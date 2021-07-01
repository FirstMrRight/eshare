package com.example.ltx.eshare.stream.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.StringJoiner;

/**
 * @Author: LiuTX
 * @Date: 2021/7/1 14:05
 */
@Slf4j
public class StringJoinerTest {
    public static void main(String[] args) {
        StringJoiner abs = new StringJoiner(",","前缀","后缀");
        abs.add("原厂自带");
        abs.add("低价购入");

        log.debug("=================StringJoiner.Merge=================");

        StringJoiner pbt = new StringJoiner(".","Start","End");
        pbt.add("双皮奶");
        pbt.add("马里奥");
        StringJoiner merge = pbt.merge(abs);
    }

}
