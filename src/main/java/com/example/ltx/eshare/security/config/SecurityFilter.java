package com.example.ltx.eshare.security.config;

import com.example.ltx.eshare.common.constant.BusinessConstant;
import com.example.ltx.eshare.common.redis.RedisService;
import com.example.ltx.eshare.module.entity.Menu;
import com.example.ltx.eshare.module.entity.Role;
import com.example.ltx.eshare.module.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author Liutx
 * @date 2020/12/17 21:54
 * @Description
 */
@Component
public class SecurityFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisService redisService;

    /**
     * Ant规则匹配符
     */
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    /**
     * 根据请求地址分析出该地址需要那些角色
     */
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> allMenus;
        allMenus = redisService.getCacheObject(BusinessConstant.REDIS_RELATED.MENU_ALL);
        if (CollectionUtils.isEmpty(allMenus)) {
            allMenus = menuService.getAllMenus();
        }
        for (Menu menu : allMenus) {
            if (pathMatcher.match(menu.getPattern(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] rolesStr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    rolesStr[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(rolesStr);
            }
        }
        //需要的角色都不满足条件，非法请求
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    /**
     *根据需要的角色查看当前用户是否具有该角色
     */
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }


}
