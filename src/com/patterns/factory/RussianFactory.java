package com.patterns.factory;

public class RussianFactory extends Factory {
    @Override
    public Car createCar() {
        return new VAZ();
    }
}
