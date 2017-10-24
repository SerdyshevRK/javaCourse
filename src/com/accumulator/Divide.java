package com.accumulator;

public class Divide implements Operation {
    @Override
    public double doOperation(double firstNumber, double secondNumber) {
        return firstNumber / secondNumber;
    }
}
