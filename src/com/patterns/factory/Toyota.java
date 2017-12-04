package com.patterns.factory;

public class Toyota extends AbstractCar {

    public Toyota() {
        System.out.println("TOYOTA - управляй мечтой.");
        litresPerKilometer = 7;
    }

    @Override
    public int drive(int distance, int fuel) {
        return fuel / litresPerKilometer;
    }
}
