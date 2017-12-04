package com.patterns.builder;

public class Pizza {
    private final String dough;
    private final String cheese;
    private final String meat;

    public static class PizzaBuilder {
        private final String dough;
        private String cheese = "no cheese";
        private String meat = "no meat";

        public PizzaBuilder(String dough) {
            this.dough = dough;
        }

        public PizzaBuilder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public PizzaBuilder meat(String meat) {
            this.meat = meat;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    public Pizza(PizzaBuilder builder) {
        this.dough = builder.dough;
        this.cheese = builder.cheese;
        this.meat = builder.meat;
    }
}
