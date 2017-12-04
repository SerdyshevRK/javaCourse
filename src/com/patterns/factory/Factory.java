package com.patterns.factory;

public abstract class Factory {

    private static JapanFactory japanFactory;
    private static GermanFactory germanFactory;
    private static RussianFactory russianFactory;

    public static Factory getFactory(String region) {
        switch (region) {
            case "RU":
                return createRussianFactory();
            case "DE":
                return createGermanFactory();
            case "JP":
                return createJapanFactory();
            default:
                return null;
        }
    }

    private static Factory createRussianFactory() {
        if (russianFactory == null)
            russianFactory = new RussianFactory();
        return russianFactory;
    }

    private static Factory createGermanFactory() {
        if (germanFactory == null)
            germanFactory = new GermanFactory();
        return germanFactory;
    }

    private static Factory createJapanFactory() {
        if (japanFactory == null)
            japanFactory = new JapanFactory();
        return japanFactory;
    }

    public abstract Car createCar();
}
