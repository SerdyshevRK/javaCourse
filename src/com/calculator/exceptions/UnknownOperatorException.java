package com.calculator.exceptions;

public class UnknownOperatorException extends Exception {
    String message = "You should use one of this operators: '+', '-', '*', '/'.";

    @Override
    public String getMessage() {
        return message;
    }
}
