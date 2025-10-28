package com.example.library.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Builder
public class ExceptionResponse {
    String message;
    HttpStatus status;
    LocalDateTime timeStamp;
    Map<String, String> errors;
    String path;
}
