package com.jobtracker.entity;

import lombok.Getter;

@Getter
public enum JobStatus {
    SAVED,
    APPLIED,
    HR_REPLIED,
    INTERVIEW,
    OFFERED,
    REJECTED,
    NO_RESPONSE;

    private String status;
}
