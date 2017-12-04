package com.collections;

public class Stack<T> {
    private final float EXTENDRATIO = 0.75f;
    private int capacity;
    private int lastElement = -1;
    private Object[] values;

    public Stack(int capacity){
        this.capacity = capacity;
        values = new Object[capacity];
    }

    private void extendValues(){
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(values, 0, newArray, 0, values.length);
        values = newArray;
    }

    public void push(T value){
        if (++lastElement >= capacity * EXTENDRATIO){
            extendValues();
        }
        values[lastElement] = value;
    }
    public T pop(){
        if (peek() == null)
            return null;
        T retVal = (T) values[lastElement];
        values[lastElement] = null;
        lastElement--;
        return retVal;
    }
    public T peek(){
        if (lastElement < 0)
            return null;
        return (T) values[lastElement];
    }
    public int size(){
        return lastElement + 1;
    }
}
