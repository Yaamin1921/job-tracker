package com.jobtracker.consumer;

import com.jobtracker.config.RabbitMQConfig;
import com.jobtracker.entity.Activity;
import com.jobtracker.entity.ActivityType;
import com.jobtracker.event.JobCreatedEvent;
import com.jobtracker.event.JobDeleteEvent;
import com.jobtracker.event.JobStatusUpdateEvent;
import com.jobtracker.service.EmailService;
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
public class JobEmailConsumer {
    private EmailService emailService;
    private final String EMAIL="mohd.yaamin@gmail.com";
    @RabbitHandler
    public void handle(JobCreatedEvent event) {


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
    public void handle(JobDeleteEvent event) {
            String subject = "Job Deleted Successfully ";
        String body = """
            Hello,

            The following job has been successfully deleted from your Job Tracker:

            Job Title : %s
            Company   : %s
            Job ID    : %s

            The job and its associated information have been removed from your tracker.

            Regards,
            Job Tracker
            """.formatted(
                event.getRole(),
                event.getCompanyName(),
                event.getJobId()
        );

            emailService.sendEmail( EMAIL,subject, body);
    }
}
