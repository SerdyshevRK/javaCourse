package com.patterns.factory;

public abstract class AbstractCar implements Car {
    int litresPerKilometer;

    @Override
    public int drive(int distance, int fuel) {
        return fuel / litresPerKilometer;
    }
}
