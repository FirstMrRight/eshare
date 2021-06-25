package com.example.ltx.eshare.security.config;

import com.example.ltx.eshare.common.constant.BusinessConstant;
import com.example.ltx.eshare.common.enums.ResponseEnum;
import com.example.ltx.eshare.common.redis.RedisService;
import com.example.ltx.eshare.common.utils.LocalDateUtils;
import com.example.ltx.eshare.module.entity.User;
import com.example.ltx.eshare.module.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Liutx
 * @date 2020/12/13 19:42
 * @Description
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisUtil;


    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private OwnAccessDecisionManager ownAccessDecisionManager;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    /**
     * 定义角色继承
     */
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_dba> ROLE_admin > ROLE_user");
        return roleHierarchy;
    }

    /**
     * 配置HTTP请求规则
     * 什么请求路径需要什么权限才能访问，
     * 登录接口，都可访问
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("user/**").hasAnyRole("admin", "user")
                .antMatchers("/test/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .usernameParameter("userName")
                .passwordParameter("passWord")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        //登陆成功处理句柄，前后分离项目，给前端返回Json即可
                        resp.setContentType("application/json;charset=utf-8");
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", HttpServletResponse.SC_OK);
                        User principal = (User) authentication.getPrincipal();
                        String token = JwtUtil.sign(principal.getUsername(), principal.getPassword());
                        map.put("msg", authentication.getPrincipal());
                        map.put("token", token);
                        map.put("userName", principal.getUsername());
                        redisUtil.setCacheObject(BusinessConstant.REDIS_RELATED.PREFIX + LocalDateUtils.getStartTimeOfDayStr() + principal.getId(), map);
                        ResponseUtil.responseJson(resp, HttpStatus.OK.value(), map);
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        //登录失败处理  AuthenticationException：锁定异常问题
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", HttpServletResponse.SC_UNAUTHORIZED);
                        if (e instanceof LockedException) {
                            map.put("msg", ResponseEnum.USER_ACCOUNT_LOCKED.getMessage());
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", ResponseEnum.USER_NOT_EXIST_OR_ERROR.getMessage());
                        } else {
                            map.put("msg", ResponseEnum.LOGIN_FILURE.getMessage());
                        }
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString("注销成功!"));
                        out.flush();
                        out.close();
                    }
                })
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(ownAccessDecisionManager);
                        o.setSecurityMetadataSource(securityFilter);
                        return o;
                    }
                })
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authException) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        //todo: 需改进
                        if (authException instanceof InsufficientAuthenticationException) {
                            throw new RuntimeException(ResponseEnum.PERMISSION_NO_ACCESS.getMessage());
                        }
                        out.write(new ObjectMapper().writeValueAsString(authException));
                        out.flush();
                        out.close();
                    }
                });
    }

}
