package com.lists;
import java.util.Iterator;

public class BidirectionalList implements List, Stack {
    Item head;
    Item tail;

    @Override
    public void add(Object object) {
        if (head == null){
            head = new Item(object);
            tail = head;
            return;
        }
        tail.next = new Item(object);
        tail.next.prev = tail;
        tail = tail.next;
        if (head.next == null)
            head.next = tail;
    }

    @Override
    public Object get(int index) {
        if (index == 0){
            return head.value;
        }
        Item item = head;
        int count = 0;
        while (count++ < index){
            item = item.next;
        }
        if (item != null)
            return item.value;
        return null;
    }

    @Override
    public Object remove(int index) {
        Object data;
        if (index == 0){
            data = head.value;
            head = head.next;
            if (head != null)
                head.prev = null;
            return data;
        }
        Item item = head;
        int count = 0;
        while (count++ < index){
            item = item.next;
        }
        if (item != null) {
            data = item.value;
            item.prev.next = item.next;
            item.next.prev = item.prev;
            return data;
        }
        return null;
    }

    @Override
    public int size() {
        if (head == null)
            return 0;
        int count = 1;
        Item item = head;
        while (item.next != null){
            count++;
            item = item.next;
        }
        return count;
    }

    @Override
    public Iterator iterator() {
        return new ClassForIterator();
    }

    @Override
    public void push(Object object) {
        add(object);
    }

    @Override
    public Object pop() {
        if (tail == null)
            return null;
        Object data = tail.value;
        tail = tail.prev;
        if (tail != null)
            tail.next = null;
        return data;
    }

    private class Item {
        Object value;
        Item next;
        Item prev;

        public Item(Object value){
            this.value = value;
            next = null;
            prev = null;
        }
    }
    class ClassForIterator implements Iterator{
        Item item = head;
        @Override
        public boolean hasNext() {
            return item != null;
        }

        @Override
        public Object next() {
            Object object = item.value;
            item = item.next;
            return object;
        }
    }
}
