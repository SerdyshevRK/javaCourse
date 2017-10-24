package com.lists;

public class Container {
    Object value;
    int index;
    Container next;

    public Container(Object value){
        this.value = value;
        this.next = null;
    }
}
