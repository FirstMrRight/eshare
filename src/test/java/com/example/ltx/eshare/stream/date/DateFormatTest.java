package com.example.ltx.eshare.stream.date;

import com.example.ltx.eshare.common.utils.DateTimeFormatterUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: LiuTX
 * @date: 2021/7/26 17:14
 * @description:
 */
public class DateFormatTest {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.parse("2021-07-26 15:20:14", DateTimeFormatterUtils.COMMON_DATE_TIME);
        System.out.println(localDateTime);
        LocalDate SEPARATOR_DATE_TIMEs = LocalDate.parse("2011-12-03", DateTimeFormatterUtils.COMMON_DATE);
        LocalDate SEPARATOR_DATE_TIME = LocalDate.parse("20200123", DateTimeFormatterUtils.SHORT_MONTH);
        System.out.println(SEPARATOR_DATE_TIMEs);
        System.out.println(SEPARATOR_DATE_TIME);
    }
}
