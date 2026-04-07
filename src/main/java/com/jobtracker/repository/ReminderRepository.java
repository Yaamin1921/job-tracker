package com.jobtracker.repository;

import com.jobtracker.entity.Reminder;
import com.jobtracker.entity.ReminderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder,Long> {
    List<Reminder> findByReminderTimeBeforeAndStatus(
            LocalDateTime time,
            ReminderStatus status
    );
}
