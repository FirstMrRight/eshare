package com.example.ltx.eshare;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.example.ltx.eshare.common.enums.ResponseEnum;
import com.example.ltx.eshare.common.model.EncryptConfig;
import com.example.ltx.eshare.common.redis.RedisService;
import com.example.ltx.eshare.module.entity.Menu;
import com.example.ltx.eshare.module.entity.Role;
import com.example.ltx.eshare.module.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EshareApplicationTests {

    @Autowired
    private RedisService redisUtil;

    @Autowired
    MenuService menuService;

    @Autowired
    EncryptConfig encryptConfig;
    @Test
    void contextLoads() {
        ArrayList<Role> arrayList = new ArrayList();
        arrayList.add(new Role().setId(1).setName("11"));
        redisUtil.setCacheObject("test", arrayList);

        Object user_uid_test = redisUtil.getCacheObject("test");
        JSON parse = JSONUtil.parse(user_uid_test);
        System.out.println(parse);
    }

    @Test
    /**
     *
     * 加密:
     * bcryptPasswordEncoder.encoder(password)
     * 解密:
     * bcrytPasswordEncoder.matches(rawPassword,encodedPassword)
     */
    void passwordEncoder() {
        String pass = "admin";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bcryptPasswordEncoder.encode(pass);
        System.out.println(encode);

        bcryptPasswordEncoder.matches("admin", encode);
        ResponseEnum.USER_NOT_EXIST_OR_ERROR.assertEquals(false, bcryptPasswordEncoder.matches("admin1", encode), ResponseEnum.USER_NOT_EXIST_OR_ERROR.getMessage());
    }

    @Test
    public void menu() {
        System.out.println(menuService.getAllMenus());
    }

    @Test
    public void menu2() {
        List<Menu> menu_all = (List<Menu>) redisUtil.getCacheObject("MENU_ALL");
        System.out.println(menu_all);
    }

    @Test
    public void test3() {
//        System.out.println(encryptConfig.getPrivatekey());
//        System.out.println(encryptConfig.getSecret());
    }
}
