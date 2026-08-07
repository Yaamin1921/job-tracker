package com.jobtracker.consumer;

import com.jobtracker.config.RabbitMQConfig;
import com.jobtracker.entity.Activity;
import com.jobtracker.entity.ActivityType;
import com.jobtracker.event.JobCreatedEvent;
import com.jobtracker.event.JobDeleteEvent;
import com.jobtracker.event.JobStatusUpdateEvent;
import com.jobtracker.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
@RabbitListener(queues = RabbitMQConfig.JOB_QUEUE)
public class ActivityConsumer {
    private final ActivityRepository activityRepository;

     @RabbitHandler
    public void handle(JobCreatedEvent event) {

        Activity activity = Activity.builder()
                .jobId(event.getJobId())
                .action(ActivityType.CREATED)
                .notes("Job created for company : " + event.getCompanyName())
                .timestamp(LocalDateTime.now())
                .build();

        activityRepository.save(activity);
        log.info("Activity created");

    }
    @RabbitHandler
    public void handle(JobStatusUpdateEvent event) {
        Activity activity = Activity.builder()
                .jobId(event.getJobId())
                .action(ActivityType.STATUS_CHANGED)
                .notes("Job status changed from: " + event.getCurrentStatus()+ "to:"+event.getNewStatus())
                .timestamp(LocalDateTime.now())
                .build();

        activityRepository.save(activity);
        log.info("job status changed successfully");

    }
    @RabbitHandler
    public void handle(JobDeleteEvent jobDeleteEvent) {
        Activity activity = Activity.builder()
                .jobId(jobDeleteEvent.getJobId())
                .action(ActivityType.DELETED)
                .notes("Job with id: "+jobDeleteEvent.getJobId() + " deleted.")
                .timestamp(LocalDateTime.now())
                .build();

        activityRepository.save(activity);
        log.info("job id: {}  successfully deleted. ",jobDeleteEvent.getJobId());

    }

}

