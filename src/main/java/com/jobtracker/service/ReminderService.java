package com.jobtracker.service;

import com.jobtracker.dto.ReminderDto;
import com.jobtracker.dto.ReminderRequest;
import com.jobtracker.entity.Reminder;
import com.jobtracker.entity.ReminderStatus;
import com.jobtracker.entity.ReminderType;

public interface ReminderService {
     ReminderDto createReminder(ReminderRequest reminderRequest);
}
