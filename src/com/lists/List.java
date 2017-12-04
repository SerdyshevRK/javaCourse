package com.lists;

import com.lists.exceptions.NullValueException;

public interface List<T> extends Iterable{
    void add(T object);
    T get(int index) throws NullValueException;
    T remove(int index);
    int size();
}
