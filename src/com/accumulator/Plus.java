package com.accumulator;

public class Plus implements Operation {
    @Override
    public double doOperation(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }
}
