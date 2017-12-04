package com.calculator.exceptions;

public class NotValidExpressionException extends Exception {
    String message = "Expression must look like this: 'x + y'.";

    @Override
    public String getMessage() {
        return message;
    }
}
