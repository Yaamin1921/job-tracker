package com.jobtracker.service;

import com.jobtracker.dto.ReminderDto;
import com.jobtracker.dto.ReminderRequest;
import com.jobtracker.entity.Reminder;

import java.util.Optional;

public interface ReminderService {
     ReminderDto createReminder(ReminderRequest reminderRequest);
     void processReminders();
     Optional<Reminder> getDueReminderByJobId(Long jobId);
}
