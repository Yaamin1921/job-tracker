package com.jobtracker.dto;

import com.jobtracker.entity.JobStatus;
import lombok.Data;

@Data
public class UpdateJobStatusRequest {
    private JobStatus status;
}