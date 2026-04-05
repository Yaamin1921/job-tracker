package com.jobtracker.repository;

import com.jobtracker.entity.TestJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestJobEntity,Long> {
}
