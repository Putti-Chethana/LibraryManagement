package com.example.user.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class ExceptionResponse {
    String message;
    HttpStatus status;
    LocalDateTime timeStamp;
    String path;
    Map<String, String> fieldErrors;
}
