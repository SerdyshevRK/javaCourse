package com.bank;

public class User {
    private static int currentID = 0;
    private int id;
    private String name;

    public User(String name) {
        this.name = name;
        this.id = currentID++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
