package com.patterns.factory;

public class FactoryMain {
    public static void main(String[] args) {

        // Russian factory
        Factory factory = Factory.getFactory("RU");
        Car car = factory.createCar();
        System.out.println(car.drive(50, 30));

        // German factory
        factory = Factory.getFactory("DE");
        car = factory.createCar();
        System.out.println(car.drive(50, 30));

        // Japan factory
        factory = Factory.getFactory("JP");
        car = factory.createCar();
        System.out.println(car.drive(50, 30));

        Factory factory1 = Factory.getFactory("JP");
        System.out.println(factory == factory1);
    }
}
