package com.patterns.factory;

public class JapanFactory extends Factory {
    @Override
    public Car createCar() {
        return new Toyota();
    }
}
