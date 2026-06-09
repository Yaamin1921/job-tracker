package com.jobtracker.repository;

import com.jobtracker.entity.DashboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<Long, DashboardEntity> {
}
