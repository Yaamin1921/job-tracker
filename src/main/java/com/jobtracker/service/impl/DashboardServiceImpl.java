package com.jobtracker.service.impl;

import com.jobtracker.dto.DashboardResponse;
import com.jobtracker.entity.JobStatus;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.service.DashboardService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final JobRepository repository;

    @Override
    @Cacheable(value = "dashboard")
    public DashboardResponse getDashboardStats() {

        long totalJobs = repository.count();

        long appliedCount =
                repository.countByStatus(JobStatus.APPLIED);

        long rejectedCount =
                repository.countByStatus(JobStatus.REJECTED);

        long responseCount =
                repository.countByStatusIn(
                        List.of(
                                JobStatus.INTERVIEW,
                                JobStatus.OFFERED
                        ));

        double responseRate = totalJobs == 0
                ? 0
                : ((double) responseCount / totalJobs) * 100;

        double rejectedPercentage = totalJobs == 0
                ? 0
                : ((double) rejectedCount / totalJobs) * 100;

        return DashboardResponse.builder()
                .totalJobs(totalJobs)
                .appliedCount(appliedCount)
                .rejectedCount(rejectedCount)
                .responseRate(
                        Math.round(responseRate * 100.0) / 100.0)
                .rejectedPercentage(
                        Math.round(rejectedPercentage * 100.0) / 100.0)
                .build();
    }
}

