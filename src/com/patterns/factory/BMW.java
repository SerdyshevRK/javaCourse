package com.patterns.factory;

public class BMW extends AbstractCar {

    public BMW() {
        System.out.println("BMW - с удовольствием за рулем.");
        litresPerKilometer = 10;
    }

    @Override
    public int drive(int distance, int fuel) {
        return fuel / litresPerKilometer;
    }
}
