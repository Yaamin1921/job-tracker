package com.jobtracker.scheduler;


import com.jobtracker.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {
    private final ReminderService reminderService;

    //@Scheduled(fixedRate = 60000)
    @Scheduled(fixedDelay = 60000)
    public void runReminderJob() {
        reminderService.processReminders();
    }
}
