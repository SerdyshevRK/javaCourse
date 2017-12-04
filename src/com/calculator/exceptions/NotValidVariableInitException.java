package com.calculator.exceptions;

public class NotValidVariableInitException extends Exception {
    String message = "Variable name should consist of characters in range: 'a-z' 'A-Z' without spaces;\n" +
            "\tVariable value should be integer number or variable which already have been initialized: 'x = 10, y = x'.";

    @Override
    public String getMessage() {
        return message;
    }
}
