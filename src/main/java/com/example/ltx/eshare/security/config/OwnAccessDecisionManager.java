package com.example.ltx.eshare.security.config;

import com.example.ltx.eshare.common.enums.ResponseEnum;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Liutx
 * @date 2020/12/19 15:14
 * @Description 为当前的访问规则进行决策，是否给予访问的权限。
 */
@Component
public class OwnAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute attribute : collection) {
            if ("ROLE_login".equals(attribute.getAttribute())) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException(ResponseEnum.PERMISSION_NOT_SAFE.getMessage());
                } else {
                    return;
                }
            }
            //查询访问所需角色，当前登录用户是否具备所需角色的其中的一个
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(attribute.getAttribute())) {
                    return;
                }
            }
            throw new AccessDeniedException(ResponseEnum.USER_ROLE_PERMISSION_ERROR.getMessage());
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
