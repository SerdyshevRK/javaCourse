package com.lists;
import com.lists.exceptions.ConcurrentModificationException;
import com.lists.exceptions.NullValueException;

import java.util.Iterator;

public class ArrayList<T> implements List<T>, Stack<T>, Queue<T> {
    private Object[] values = new Object[5];
    private int lastElement = -1;

    private Object[] extendValues(){
        Object[] retArray = new Object[values.length * 2];
        System.arraycopy(values, 0, retArray, 0, values.length);
        return retArray;
    }

    public void trimToSize() {
        Object[] trimArray;
        if (lastElement + 1 < values.length) {
            trimArray = new Object[lastElement + 1];
            System.arraycopy(values, 0, trimArray, 0, trimArray.length);
            values = trimArray;
        }
    }

    @Override
    public void add(T object) {
        if (++lastElement >= values.length)
            values = extendValues();
        values[lastElement] = object;
    }

    @Override
    public T get(int index) throws NullValueException {
        T retVal = (T) values[index];
        if (retVal != null)
            return retVal;
        throw new NullValueException();
    }

    @Override
    public T remove(int index) {
        T retValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, values.length - index);
        return retValue;
    }

    @Override
    public int size() {
        return lastElement + 1;
    }

    @Override
    public void enqueue(T object) {
        add(object);
    }

    @Override
    public T dequeue() {
        T retValue = (T) values[0];
        System.arraycopy(values, 1, values, 0, lastElement);
        lastElement--;
        return retValue;
    }

    @Override
    public T queuePeek() {
        return (T) values[0];
    }

    @Override
    public void push(T object) {
        add(object);
    }

    @Override
    public T pop() {
        T retValue = (T) values[lastElement];
        values[lastElement] = null;
        lastElement--;
        return retValue;
    }

    @Override
    public T peek() {
        return (T) values[lastElement];
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int index = 0;
            int check = lastElement;

            @Override
            public boolean hasNext() {
                if (check != lastElement)
                    throw new ConcurrentModificationException();
                return index <= lastElement;
            }

            @Override
            public Object next() {
                T retValue = (T) values[index];
                index++;
                return retValue;
            }
        };
    }
}
