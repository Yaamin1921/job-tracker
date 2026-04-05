package com.jobtracker.util;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;


@Data
@Builder
public class ErrorResponse {
    private String message;
    private LocalDate localDate;
    private HttpStatus httpStatus;

}
