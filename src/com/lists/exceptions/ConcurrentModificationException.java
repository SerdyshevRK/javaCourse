package com.lists.exceptions;

public class ConcurrentModificationException extends RuntimeException {
    String message = "What are you doing? 0_o";

    @Override
    public String getMessage() {
        return message;
    }
}
