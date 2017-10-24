package com.accumulator;

public class Power implements Operation{
    @Override
    public double doOperation(double firstNumber, double secondNumber) {
        return Math.pow(firstNumber, secondNumber);
    }
}
