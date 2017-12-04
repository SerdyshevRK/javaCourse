package com.reflection.dependency;

public class DIConcreteClass implements DIInterfaceExample {
    @Override
    public String getValue() {
        return String.valueOf(Math.random());
    }
}
