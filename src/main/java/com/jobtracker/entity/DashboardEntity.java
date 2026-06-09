package com.jobtracker.entity;


import jakarta.persistence.Table;
import lombok.Builder;

import java.math.BigDecimal;
@Table(name="dashboard")
@Builder
import java.math.BigDecimal;

public class DashboardEntity {
    private BigDecimal jobsCount;
    private BigDecimal appliedCount;
    private BigDecimal responseCount;
    private BigDecimal rejectionRate;
}
