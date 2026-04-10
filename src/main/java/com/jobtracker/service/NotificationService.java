package com.jobtracker.service;

import com.jobtracker.dto.ReminderDto;
import com.jobtracker.entity.Reminder;

public interface NotificationService {

    void sendReminder(Reminder reminder);
}
