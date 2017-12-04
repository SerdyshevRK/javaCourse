package com.patterns.builder;

public class BuilderMain {
    public static void main(String[] args) {
        Pizza pizza = new Pizza
                .PizzaBuilder("someDough")
                .cheese("cheder")
                .meat("beaf")
                .build();
    }
}
