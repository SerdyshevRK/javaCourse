package com.lists;

public class ArrayList implements com.lists.List, com.lists.Stack, com.lists.Queue {
    private Object[] values = new Object[5];
    private int lastElement = -1;

    @Override
    public void add(Object object) {
        if (++lastElement > values.length - 1){
            values = extendValuesArray();
        }
        values[lastElement] = object;
    }

    @Override
    public Object pull() {
        if (values[0] == null)
            return null;
        Object retVal = values[0];
        System.arraycopy(values, 1, values, 0, lastElement);
        values[values.length - 1] = null;
        lastElement--;
        return retVal;
    }

    @Override
    public Object get(int index) {
        if (index > lastElement)
            return null;
        return values[index];
    }

    @Override
    public Object remove(int index) {
        if (index > lastElement)
            return null;
        Object retVal = values[index];
        System.arraycopy(values, index + 1, values, index, lastElement - index);
        values[values.length - 1] = null;
        lastElement--;
        return retVal;
    }

    @Override
    public int size() {
        return lastElement + 1;
    }

    @Override
    public void push(Object object) {
        add(object);
    }

    @Override
    public Object pop() {
        Object retVal = values[lastElement];
        values[lastElement] = null;
        lastElement--;
        return retVal;
    }

    private Object[] extendValuesArray(){
        Object[] retArray = new Object[values.length * 2];
        System.arraycopy(values, 0, retArray, 0, values.length);
        return retArray;
    }
}
