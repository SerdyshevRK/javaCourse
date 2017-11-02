package com.lists;

public interface Queue<T> {
    void enqueue(T object);
    T dequeue();
    T queuePeek();
}
