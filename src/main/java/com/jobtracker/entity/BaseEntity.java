package com.jobtracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist //	Before an INSERT
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate //	Before an update
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}