package com.patterns.factory;

public class VAZ extends AbstractCar {

    public VAZ() {
        System.out.println("ВАЗ - не ссы доедем.");
        litresPerKilometer = 20;
    }

    @Override
    public int drive(int distance, int fuel) {
        return fuel / litresPerKilometer;
    }
}
