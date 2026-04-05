package com.jobtracker.service;

import com.jobtracker.dto.JobDto;
import com.jobtracker.entity.Job;
import com.jobtracker.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public JobDto createJob(JobDto jobDto) {
        Job job = mapToEntity(jobDto);
        Job savedJob = jobRepository.save(job);
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
    public JobDto updateJobStatus(Long id, String status) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        job.setStatus(status);
        Job updated = jobRepository.save(job);

        return mapToDto(updated);
    }

    @Override
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found with id: " + id);
        }
        jobRepository.deleteById(id);
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