package com.example.library.exception;

public class InvalidFieldException extends RuntimeException{

    public InvalidFieldException(String message) {
        super(message);
    }
}
