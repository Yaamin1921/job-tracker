package com.jobtracker.repository;

import com.jobtracker.entity.Job;
import com.jobtracker.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job,Long> {

        long countByStatus(JobStatus status);
        long countByStatusIn(List<JobStatus> statuses);
        List<Job> findAllByOwner_Id(long userId);
        Optional<Job> findByIdAndOwner(Long id,Long owner);
}
