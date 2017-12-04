package com.patterns.factory;

public class GermanFactory extends Factory {
    @Override
    public Car createCar() {
        return new BMW();
    }
}
