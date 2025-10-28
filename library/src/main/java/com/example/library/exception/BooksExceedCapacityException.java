package com.example.library.exception;

public class BooksExceedCapacityException extends RuntimeException{
    public BooksExceedCapacityException(String message) {
        super(message);
    }
}
