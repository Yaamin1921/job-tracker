package com.jobtracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private long totalJobs;
    private long appliedCount;
    private long rejectedCount;
    private double responseRate;
    private double rejectedPercentage;
}