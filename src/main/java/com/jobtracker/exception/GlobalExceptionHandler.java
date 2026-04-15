package com.jobtracker.exception;

import com.jobtracker.util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex) {
        var message=ErrorResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .localDate(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(message);
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorResponse> JobGlobalExceptionHandler(Exception ex){
        var message=ErrorResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .localDate(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(message);
    }

}