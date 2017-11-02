package com.lists;

public class Item<T> {
    T value;
    Item next;
    Item prev;

    public Item(T value) {
        this.value = value;
        next = null;
        prev = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;
        if (this.value.getClass() != ((Item<T>) obj).value.getClass())
            return false;
        if (this.value.hashCode() != ((Item<T>) obj).value.hashCode())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int retValue = 0;
        retValue += this.value.hashCode();
        retValue += this.next.value.hashCode();
        retValue += this.prev.value.hashCode();
        return retValue;
    }
}
