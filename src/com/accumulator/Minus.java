package com.accumulator;

public class Minus implements Operation {
    @Override
    public double doOperation(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }
}
