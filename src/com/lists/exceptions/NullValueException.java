package com.lists.exceptions;

public class NullValueException extends Exception {
    String message = "Element is not in the collection.";

    @Override
    public String getMessage() {
        return message;
    }
}
