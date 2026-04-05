package com.jobtracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JobDto {

    private Long id;
    private String companyName;
    private String role;
    private String status;
    private LocalDate appliedDate;
    private String source;
}
