package com.jobtracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String type;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
}