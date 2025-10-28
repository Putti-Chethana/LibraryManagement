package com.example.library.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).timeStamp(LocalDateTime.now()).path(request.getRequestURI()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BooksExceedCapacityException.class)
    public ResponseEntity<Object> handleBooksExceeding(BooksExceedCapacityException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message(ex.getMessage()).status(HttpStatus.OK).timeStamp(LocalDateTime.now()).path(request.getRequestURI()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }

    @ExceptionHandler(BookAlreadyGivenToUserException.class)
    public ResponseEntity<ExceptionResponse> handleBookGiven(BookAlreadyGivenToUserException ex, HttpServletRequest request) {
        log.info("error sricharan");
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message(ex.getMessage()).status(HttpStatus.OK).timeStamp(LocalDateTime.now()).path(request.getRequestURI()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }

    @ExceptionHandler(BookNotAllotedToUserException.class)
    public ResponseEntity<Object> handleBookNotAlloted(BookNotAllotedToUserException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message(ex.getMessage()).status(HttpStatus.OK).timeStamp(LocalDateTime.now()).path(request.getRequestURI()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
}
