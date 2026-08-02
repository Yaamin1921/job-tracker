package com.jobtracker.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCreatedEvent implements Serializable {
    private Long jobId;

    private String companyName;

    private String role;

    private LocalDateTime createdAt;
}
