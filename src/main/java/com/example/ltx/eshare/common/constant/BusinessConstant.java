package com.example.ltx.eshare.common.constant;

/**
 * @author Liutx
 * @date 2020/12/19 15:04
 * @Description
 */
public interface BusinessConstant {


    /**
     * Redis名称
     */
    interface REDIS_RELATED {
        String MENU_ALL = "MENU_ALL";

        long CACHE_TIME = 60 * 60 * 24 * 3;

        String PREFIX = "USER_UID_";
    }

}