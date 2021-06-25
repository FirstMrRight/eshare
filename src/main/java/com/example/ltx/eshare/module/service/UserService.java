package com.example.ltx.eshare.module.service;

import com.example.ltx.eshare.common.enums.ResponseEnum;
import com.example.ltx.eshare.module.entity.User;
import com.example.ltx.eshare.module.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Liutx
 * @date 2020/12/13 19:20
 * @Description
 */

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUserName(userName);
        ResponseEnum.PERSONAL_NAME_NULL.assertNotNull(user);
        return user.setRoles(userMapper.getUserRolesById(user.getId()));
    }

    public User getUser(Integer userId) {
        return userMapper.getUser(userId);
    }
}
