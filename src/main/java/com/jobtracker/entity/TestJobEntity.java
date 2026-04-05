package com.jobtracker.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestJobEntity extends BaseJobEntity {

    private String name;
}
