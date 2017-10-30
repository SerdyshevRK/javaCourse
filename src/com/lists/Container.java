package com.lists;

public class Container {
    Object value;
    int index;
    Container next;

    public Container(Object value){
        this.value = value;
        this.next = null;
    }

    @Override
    public int hashCode() {
        int retVal = 0;
        retVal += value.hashCode();
        retVal += next.value.hashCode();
        return retVal;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Container))
            return false;
        return this.value.hashCode() == ((Container) obj).value.hashCode();
    }
}
