package com.jobtracker.service.impl;

import com.jobtracker.entity.Reminder;
import com.jobtracker.service.NotificationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendReminder(Reminder reminder) {
        log.info("Remind triggered for JobID: {} | type: {} | Time: {}",reminder.getJobId(),reminder.getType(),reminder.getReminderTime());
    }
}
