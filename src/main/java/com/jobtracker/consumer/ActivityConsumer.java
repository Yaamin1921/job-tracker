package com.jobtracker.consumer;

import com.jobtracker.entity.Activity;
import com.jobtracker.entity.ActivityType;
import com.jobtracker.event.JobCreatedEvent;
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
