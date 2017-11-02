package com.lists;

public interface List<T> extends Iterable{
    void add(T object);
    T get(int index);
    T remove(int index);
    int size();
}
