package com.example.ltx.eshare.module.mapper;

import com.example.ltx.eshare.module.entity.Role;
import com.example.ltx.eshare.module.entity.User;
import com.example.ltx.eshare.module.entity.UserDto;

import java.util.List;

/**
 * @author Liutx
 * @date 2020/12/13 19:22
 * @Description
 */
public interface UserMapper {
    User loadUserByUserName(String userName);

    List<Role> getUserRolesById(Integer id);

    List<UserDto> getUserById(Integer id);

}
