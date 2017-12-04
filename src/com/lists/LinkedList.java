package com.lists;

import com.lists.exceptions.ConcurrentModificationException;
import com.lists.exceptions.NullValueException;
import java.util.Iterator;

public class LinkedList<T> implements List<T>, Stack<T>, Queue<T> {
    Item head;
    Item tail;
    int length;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public int size(){
        int count = 0;
        Item item = head;
        while (item.next != null){
            count++;
            item = item.next;
        }
        return count;
    }

    @Override
    public void add(T object) {
        if (head == null){
            head = new Item(object);
            tail = head;
            return;
        }
        Item item = tail;
        item.next = new Item(object);
        tail = item.next;
        tail.prev = item;
        length++;
    }

    @Override
    public T get(int index) throws NullValueException {
        if (index == 0) {
            if (head != null)
                return (T) head.value;
            throw new NullValueException();
        }

        Item item = findItem(index);
        if (item != null)
            return (T) item.value;
        throw new NullValueException();
    }

    @Override
    public T remove(int index) {
        Object retValue;

        if (index == 0){
            retValue = head.value;
            head = head.next;
            if (head != null)
                head.prev = null;
            length--;
            return (T) retValue;
        }

        Item item = findItem(index);
        retValue = item.value;
        item.prev.next = item.next;
        item.next.prev = item.prev;
        return (T) retValue;
    }

    private Item findItem(int index){
        Item item = head.next;
        int count = 1;
        while (count != index){
            item = item.next;
            count++;
        }
        return item;
    }

    @Override
    public void push(T object) {
        add(object);
    }

    @Override
    public T pop() {
        Object retValue = peek();
        if (retValue != null) {
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
        }
        length--;
        return (T) retValue;
    }

    @Override
    public T peek() {
        if (tail == null)
            return null;

        return (T) tail.value;
    }

    @Override
    public void enqueue(T object) {
        add(object);
    }

    @Override
    public T dequeue() {
        Object retValue = queuePeek();
        if (retValue != null){
            head = head.next;
            if (head != null)
                head.prev = null;
        }
        length--;
        return (T) retValue;
    }

    @Override
    public T queuePeek() {
        if (head == null)
            return null;

        return (T) head.value;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Item item = head;
            int check = length;

            @Override
            public boolean hasNext() {
                if (check != length)
                    throw new ConcurrentModificationException();
                return item != null;
            }

            @Override
            public Object next() {
                Object retValue = item.value;
                item = item.next;
                return retValue;
            }
        };
    }

    @Override
    public int hashCode() {
        int retValue = 0;
        Item item = head;
        while (item.next != null){
            retValue += item.hashCode();
            item = item.next;
        }
        return retValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;
        if (this.hashCode() != ((LinkedList<T>) obj).hashCode())
            return false;

        Item item = head;
        Item objItem = ((LinkedList<T>) obj).head;
        while (item.next != null){
            if (!item.value.equals(objItem.value))
                return false;
            item = item.next;
            objItem = objItem.next;
        }
        return true;
    }
}
