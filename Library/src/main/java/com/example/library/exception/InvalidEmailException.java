package com.example.library.exception;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(String message) {
        super(message);
    }
}
