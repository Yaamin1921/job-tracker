package com.jobtracker.util;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
public class ErrorResponse {
    private String message;
    private LocalDateTime localDate;
    private HttpStatus httpStatus;

}
