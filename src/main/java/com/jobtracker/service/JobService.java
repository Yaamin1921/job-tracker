package com.jobtracker.service;

import com.jobtracker.dto.ActivityDto;
import com.jobtracker.dto.JobDto;
import com.jobtracker.entity.Activity;
import com.jobtracker.entity.JobStatus;

import java.util.List;

public interface JobService {
    JobDto createJob(JobDto jobDto);

    List<JobDto> getAllJobs();

    JobDto getJobById(Long id);

    JobDto updateJobStatus(Long id, JobStatus status);

    void deleteJob(Long id);

    List<ActivityDto> getTimeline(Long id);
}
