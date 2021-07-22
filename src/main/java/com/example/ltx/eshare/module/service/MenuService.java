package com.example.ltx.eshare.module.service;

import com.example.ltx.eshare.common.redis.RedisUtil;
import com.example.ltx.eshare.module.entity.Menu;
import com.example.ltx.eshare.module.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Liutx
 * @date 2020/12/17 22:03
 * @Description
 */

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RedisUtil redisUtil;

    public List<Menu> getAllMenus() {
        List<Menu> allMenus = menuMapper.getAllMenus();
        redisUtil.setCacheObject("MENU_ALL", allMenus);
        return allMenus;
    }
}
