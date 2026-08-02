package com.jobtracker.consumer;

import com.jobtracker.entity.Activity;
import com.jobtracker.entity.ActivityType;
import com.jobtracker.event.JobCreatedEvent;
import com.jobtracker.event.JobStatusUpdateEvent;
import com.jobtracker.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActivityConsumer {
    private final ActivityRepository activityRepository;

    public void consume(JobCreatedEvent event) {

        Activity activity = Activity.builder()
                .jobId(event.getJobId())
                .action(ActivityType.CREATED)
                .notes("Job created for company : " + event.getCompanyName())
                .timestamp(LocalDateTime.now())
                .build();

        activityRepository.save(activity);
        log.info("Activity created");

    }
    public void jobStatusConsume(JobStatusUpdateEvent event) {

        Activity activity = Activity.builder()
                .jobId(event.getJobId())
                .action(ActivityType.STATUS_CHANGED)
                .notes("Job status changed from: " + event.getCurrentStatus()+ "to:"+event.getNewStatus())
                .timestamp(LocalDateTime.now())
                .build();

        activityRepository.save(activity);
        log.info("job status changed successfully");

    }
    /*POST /jobs
      │
      ▼
JobController
      │
      ▼
JobService
      │
      ▼
Save Job
      │
      ▼
Publish JobCreatedEvent
      │
      ▼
RabbitMQ
      │
      ▼
ActivityConsumer
      │
      ▼
Save Activity*/
}
