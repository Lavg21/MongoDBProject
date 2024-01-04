package com.example.library.exception;

public class InvalidNameException extends RuntimeException{

    public InvalidNameException(String message) {
        super(message);
    }
}
