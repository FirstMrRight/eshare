package com.example.ltx.eshare.stream.date;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: LiuTX
 * @date: 2021/7/23 14:49
 * @description:
 */

@Slf4j
public class DateTest {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        log.info("defaultLocale-{}", Locale.getDefault());

        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 29, 0, 0, 0);
        SimpleDateFormat YYYY = new SimpleDateFormat("YYYY-MM-dd");
        log.info("DECEMBER格式化:{}", YYYY.format(calendar.getTime()));

        SimpleDateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd");
        log.info("格式化:{}", yyyy.format(calendar.getTime()));

        String dateString = "20160901";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        log.info("result:{}", dateFormat.parse(dateString));


        System.out.println(LocalDate.now().plusDays(1).minus(Period.ofDays(1)).minusMonths(2).plus(Period.ofYears(1)));

        System.out.println("计算时间差");
        LocalDate specifyDate = LocalDate.of(2019, 10, 1);
        System.out.println(Period.between(LocalDate.now(), specifyDate).getDays());
        LocalDate today = LocalDate.of(2019, 12, 12);
        System.out.println(Period.between(specifyDate, today).getDays());
        System.out.println(Period.between(specifyDate, today));
        System.out.println(ChronoUnit.DAYS.between(specifyDate, LocalDate.now()));

        System.out.println("//使用lambda表达式自定义逻辑");
        System.out.println(LocalDate.now().with(temporal -> temporal.plus(ThreadLocalRandom.current().nextInt(100), ChronoUnit.DAYS)));
    }
}
