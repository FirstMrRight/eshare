package com.example.ltx.eshare.stream;

import lombok.Data;

import java.util.Optional;

/**
 * @Author: LiuTX
 * @Date: 2021/6/25 11:10
 */
@Data
public class Person {
    private String name;
    private int age;
    private String password;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
