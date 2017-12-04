package com.reflection.exceptions;

public class ClassBuildException extends Exception {
    private String message = "Class cannot be built.";

    @Override
    public String getMessage() {
        return message;
    }
}
