package com.multithreading.eatery;

public class Waiter {
    private String order;

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void deliver() {
        System.out.println(order + " was delivered.");
    }
}
