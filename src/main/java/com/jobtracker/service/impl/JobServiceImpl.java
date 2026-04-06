package com.jobtracker.service.impl;

import com.jobtracker.dto.ActivityDto;
import com.jobtracker.dto.JobDto;
import com.jobtracker.entity.Activity;
import com.jobtracker.entity.ActivityType;
import com.jobtracker.entity.Job;
import com.jobtracker.entity.JobStatus;
import com.jobtracker.repository.ActivityRepository;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final ModelMapper mapper;
    private final JobRepository jobRepository;
    private final ActivityRepository activityRepository;

    // ✅ Allowed transitions map
    private static final EnumMap<JobStatus, Set<JobStatus>> allowedTransitions = new EnumMap<>(JobStatus.class);

    static {
        allowedTransitions.put(JobStatus.SAVED, Set.of(JobStatus.APPLIED));

        allowedTransitions.put(JobStatus.APPLIED, Set.of(
                JobStatus.HR_REPLIED,
                JobStatus.REJECTED,
                JobStatus.NO_RESPONSE));
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




    @Override
    public JobDto createJob(JobDto jobDto) {
        Job job = mapToEntity(jobDto);
        Job savedJob = jobRepository.save(job);

        Activity activity = Activity.builder()
                .jobId(savedJob.getId())
                .action(ActivityType.CREATED)
                .notes("Job created for company: " + job.getCompanyName())
                .timestamp(LocalDateTime.now())
                .build();

        activityRepository.save(activity);

        return mapToDto(savedJob);
    }

    @Override
    public List<JobDto> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public JobDto getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
        return mapToDto(job);
    }

    @Override
    public JobDto updateJobStatus(Long jobId, JobStatus newStatus) {
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
        var jobs= jobRepository.save(job);

        // 🔥 CREATE ACTIVITY
        Activity activity = Activity.builder()
                .jobId(jobId)
                .action(ActivityType.STATUS_CHANGED)
                .notes("Status changed from " + currentStatus + " to " + newStatus)
                .timestamp(LocalDateTime.now())
                .build();

        activityRepository.save(activity);

        return mapToDto(jobs);
    }
    private boolean isValidTransition(JobStatus current, JobStatus next) {
        return allowedTransitions.getOrDefault(current, Set.of()).contains(next);
    }

    @Override
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found with id: " + id);
        }
        jobRepository.deleteById(id);
    }

    @Override
    public List<ActivityDto> getTimeline(Long id) {
        var activities=activityRepository.findByJobIdOrderByTimestampDesc(id);
        var activityDtoList=activities.stream().map(entity->mapper.map(entity,ActivityDto.class)).toList();
        return activityDtoList;

    }

    // 🔁 Mappers

    private Job mapToEntity(JobDto dto) {
        return Job.builder()
                .id(dto.getId())
                .companyName(dto.getCompanyName())
                .role(dto.getRole())
                .status(dto.getStatus())
                .appliedDate(dto.getAppliedDate())
                .source(dto.getSource())
                .build();
    }

    private JobDto mapToDto(Job job) {
        JobDto dto = new JobDto();
        dto.setId(job.getId());
        dto.setCompanyName(job.getCompanyName());
        dto.setRole(job.getRole());
        dto.setStatus(job.getStatus());
        dto.setAppliedDate(job.getAppliedDate());
        dto.setSource(job.getSource());
        return dto;
    }
}