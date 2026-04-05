package com.jobtracker.repository;

import com.jobtracker.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByJobIdOrderByTimestampDesc(Long jobId);
}