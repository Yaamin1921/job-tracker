package com.jobtracker.repository;

import com.jobtracker.entity.Reminder;
import com.jobtracker.entity.ReminderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ReminderRepository extends JpaRepository<Reminder,Long> {
    List<Reminder> findByReminderTimeBeforeAndStatus(
            OffsetDateTime time,
            ReminderStatus status
    );

    Optional<Reminder> findByJobId(Long jobId);
}
