package com.accumulator;

public class Accumulator {
    private double value;
    private Operation operation;

    public Accumulator(double value, Operation operation){
        this.operation = operation;
        this.value = value;
    }

    public void setOperation(Operation operation){
        this.operation = operation;
    }
    public void accumulate(double number){
        value = operation.doOperation(value, number);
    }
    public double getValue(){
        return this.value;
    }
}
