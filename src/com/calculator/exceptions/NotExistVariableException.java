package com.calculator.exceptions;

public class NotExistVariableException extends Exception{
    String message = "One or both variables are not initialized.";

    @Override
    public String getMessage() {
        return message;
    }
}
