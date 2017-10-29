package com.lists;

import java.util.Iterator;

public class LinkedList implements com.lists.List, com.lists.Stack, com.lists.Queue {
    private com.lists.Container head;
    private int length = 0;

    @Override
    public void add(Object object){                     // add new element to the end of list
        com.lists.Container container;
        if (head == null){
            head = new com.lists.Container(object);
        } else {
            container = head;
            while (container.next != null){
                container = container.next;
            }
            container.next = new com.lists.Container(object);
        }
        length = indexLoader(head);
    }

    @Override
    public Object pull() {
        return pop();
    }

    @Override
    public Object get(int index){
        com.lists.Container container;
        if (index == 0 && head != null){
            return head.value;
        } else {
            if (head == null)
                return null;
            container = head;
            while (container.next != null){
                if (container.index == index)
                    break;
                container = container.next;
            }
            if (container.index != index)
                return null;
            return container.value;
        }
    }

    @Override
    public Object remove(int index){
        Object retVal;
        com.lists.Container container;
        if (index == 0){
            retVal = head;
            head = head.next;
            return retVal;
        }
        container = head;
        while (container.next.next != null){
            if (container.next.index == index)
                break;
            container = container.next;
        }
        if (container.next.index != index)
            return null;
        retVal = container.next.value;
        container.next = container.next.next;
        length = indexLoader(head);
        return retVal;
    }

    private int indexLoader(com.lists.Container container){
        int index = 0;
        container.index = index;
        index++;
        while (container.next != null){
            container = container.next;
            container.index = index;
            index++;
        }
        return index;
    }

    @Override
    public int size(){
        return length;
    }

    @Override
    public void push(Object object) {
        if (head == null){
            head = new com.lists.Container(object);
            return;
        }
        com.lists.Container container = new com.lists.Container(object);
        container.next = head;
        head = container;
    }

    @Override
    public Object pop() {
        if (head == null){
            return null;
        }
        Object retVal = head.value;
        head = head.next;
        return retVal;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Container container = LinkedList.this.head;

            @Override
            public boolean hasNext() {
                if (container != null)
                    return true;
                return false;
            }

            @Override
            public Object next() {
                Object retVal = container.value;
                container = container.next;
                return retVal;
            }
        };
    }
}
