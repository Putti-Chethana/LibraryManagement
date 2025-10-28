package com.example.library.exception;

public class BookAlreadyGivenToUserException extends RuntimeException{
    public BookAlreadyGivenToUserException(String message) {
        super(message);
    }
}
