package com.jobtracker.service.impl;

import com.jobtracker.entity.Job;
import com.jobtracker.entity.JobStatus;
import com.jobtracker.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class JobStatusServiceImpl {
    private final JobRepository jobRepository;

    // ✅ Allowed transitions map
    private static final EnumMap<JobStatus, Set<JobStatus>> allowedTransitions = new EnumMap<>(JobStatus.class);

    static {
        allowedTransitions.put(JobStatus.SAVED, Set.of(JobStatus.APPLIED));

        allowedTransitions.put(JobStatus.APPLIED, Set.of(
                JobStatus.HR_REPLIED,
                JobStatus.REJECTED,
                JobStatus.NO_RESPONSE
        ));

        allowedTransitions.put(JobStatus.HR_REPLIED, Set.of(
                JobStatus.INTERVIEW,
                JobStatus.REJECTED
        ));

        allowedTransitions.put(JobStatus.INTERVIEW, Set.of(
                JobStatus.OFFERED,
                JobStatus.REJECTED
        ));

        allowedTransitions.put(JobStatus.OFFERED, Set.of()); // final
        allowedTransitions.put(JobStatus.REJECTED, Set.of()); // final
        allowedTransitions.put(JobStatus.NO_RESPONSE, Set.of()); // final
    }

    // ✅ Update Status Method
    public Job updateJobStatus(Long jobId, JobStatus newStatus) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));

        JobStatus currentStatus = job.getStatus();

        // 🔥 VALIDATION
        if (!isValidTransition(currentStatus, newStatus)) {
            throw new IllegalStateException(
                    "Invalid status transition from " + currentStatus + " to " + newStatus
            );
        }

        job.setStatus(newStatus);

        return jobRepository.save(job);
    }

    private boolean isValidTransition(JobStatus current, JobStatus next) {
        return allowedTransitions.getOrDefault(current, Set.of()).contains(next);
    }
}
