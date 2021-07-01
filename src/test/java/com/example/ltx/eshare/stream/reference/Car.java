package com.example.ltx.eshare.stream.reference;

/**
 * @Author: LiuTX
 * @Date: 2021/6/30 13:49
 */
@FunctionalInterface
interface Supplier<T> {
    T get();
}


class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    final Car car = Car.create(Car::new);
}
