package com.jobtracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "job_tracker", name = "activities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    /*@ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;*/   //not require is it will crate unnecessary complexity and d timeline is seperate api..also in job not require to show activity.

    @Enumerated(EnumType.STRING)
    private ActivityType action;

    private String notes;

    private LocalDateTime timestamp;
}