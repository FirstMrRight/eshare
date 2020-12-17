package com.example.ltx.eshare.module.service;

import com.example.ltx.eshare.common.redis.RedisService;
import com.example.ltx.eshare.common.utils.LocalDateUtils;
import com.example.ltx.eshare.module.entity.Menu;
import com.example.ltx.eshare.module.mapper.MenuMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    RedisService redisService;

    public List<Menu> getAllMenus() {
        List<Menu> allMenus = menuMapper.getAllMenus();
        redisService.setCacheObject("MENU_ALL", allMenus);
        return allMenus;
    }
}
