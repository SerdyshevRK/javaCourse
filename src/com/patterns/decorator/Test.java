package com.patterns.decorator;

import java.io.Serializable;

class Test implements Serializable {
    int intVal;
    double doubleVal;
    String string;
    boolean boolVal;

    public Test(int intVal, double doubleVal, String string, boolean boolVal) {
        this.intVal = intVal;
        this.doubleVal = doubleVal;
        this.string = string;
        this.boolVal = boolVal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Test:\n")
                .append("int = " + this.intVal + ", ")
                .append("double = " + this.doubleVal + ", ")
                .append("string = " + this.string + ", ")
                .append("boolean = " + this.boolVal);
        return sb.toString();
    }
}
