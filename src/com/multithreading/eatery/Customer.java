package com.multithreading.eatery;

public class Customer {
    private String order;

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void eating() {
        System.out.println("finally I can eat!");
    }
}
