package com.multithreading.eatery;

public class Cook {
    private String order;

    public void setOrder(String order) {
        this.order = order;
    }

    public void prepare() {
        System.out.println(order + " was cooked.");
    }
}
