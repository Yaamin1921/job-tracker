package com.jobtracker.dto;

import com.jobtracker.entity.JobStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobDto {

    private Long id;
    private String companyName;
    private String role;
    private JobStatus status;
    private LocalDate appliedDate;
    private String source;
}
