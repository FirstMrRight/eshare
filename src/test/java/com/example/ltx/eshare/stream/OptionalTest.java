package com.example.ltx.eshare.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @Author: LiuTX
 * @Date: 2021/6/24 14:45
 */
@Slf4j
public class OptionalTest {
    public static void main(String[] args) {
        String name = Optional.of("baeldung").orElse(getRandomName());
        log.info(name);
    }

    private static String getRandomName() {
        List<String> names = new ArrayList<>();
        names.add("zhangsan");
        names.add("李四");
        names.add("wangwu");

        log.info("getRandomName() method - start");

        Random random = new Random();
        int index = random.nextInt(3);
        log.debug("getRandomName() method - end");


        return names.get(index);
    }

    @Test
    public void test2() {
        String password = " password ";
        Optional<String> passOpt = Optional.of(password);
        boolean correctPassword = passOpt.filter(pass -> pass.equals("password")).isPresent();
        boolean password1 = passOpt.map(String::trim).filter(pass -> pass.equals("password")).isPresent();
        log.info(String.valueOf(correctPassword));
        log.info(String.valueOf(password1));
    }

    @Test
    public void test3() {
        Person person = new Person("NICK", 22);
        Optional<Person> personOptional = Optional.ofNullable(person);
        Optional<String> optionalName = personOptional
                .map(Person::getName)
                .orElseThrow(() -> new RuntimeException("你nameOptionalWrapper女朋友没名字！"));

        String name = personOptional.flatMap(Person::getName).orElse("");
        log.info(name);
    }

    @Test
    public void test4() {

        Integer a = null;
        String value = String.valueOf(a);
        log.info(value);

        Person person = new Person();
        person.setName(value == null ? null : value);
    }
}
