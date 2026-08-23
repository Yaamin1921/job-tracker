package com.jobtracker.consumer;

import com.jobtracker.config.RabbitMQConfig;
import com.jobtracker.entity.EmailStatus;
import com.jobtracker.event.EmailEvent;
import com.jobtracker.event.JobCreatedEvent;
import com.jobtracker.event.JobDeleteEvent;
import com.jobtracker.event.JobStatusUpdateEvent;
import com.jobtracker.service.EmailProcessService;
import com.jobtracker.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@RabbitListener(queues = RabbitMQConfig.Email_QUEUE,autoStartup = "${email.consumer.enabled:true}",
        containerFactory = "rabbitListenerContainerFactory")
public class JobEmailConsumer {
    @Autowired
    private EmailService emailService;
    private static final String EMAIL="yaamin1921413@gmail.com";
    @Autowired
    private final EmailProcessService emailEventService;
    @RabbitHandler
    public void handle(JobCreatedEvent event) {

        String subject = "Job added Successfully ";
        String body = """
            Hello,
            
            The following job has been successfully added: 

            jobId : %s
            companyName   : %s
            role    : %s
            
            Regards,
            Job Tracker
            """.formatted(
                event.getJobId(),
                event.getCompanyName(),
                event.getRole()
        );
        emailEventHandle(subject,body,event.getEventId());

    }
    @RabbitHandler
    public void handle(JobStatusUpdateEvent event) {

        String subject = "Job Status updated Successfully ";
        String body = """
            Hello,
            
            The following job has been successfully updated: 

            from : %s
            to   : %s
            Job ID    : %s
            
            Regards,
            Job Tracker
            """.formatted(
                event.getCurrentStatus(),
                event.getNewStatus(),
                event.getJobId()
        );

        emailEventHandle(subject,body,event.getEventId());
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

            Regards,
            Job Tracker
            """.formatted(
                event.getRole(),
                event.getCompanyName(),
                event.getJobId()
        );

         emailEventHandle(subject,body,event.getEventId());

    }

    public void emailEventHandle(String subject,String body, UUID eventId){
        EmailEvent emailEvent =
                emailEventService.getOrCreate(eventId);

        if (emailEvent.getStatus() == EmailStatus.SENT) {
            log.info("Email already sent for event {}", eventId);
            return;
        }

        try {
            emailEventService.markProcessing(eventId);
            emailService.sendEmail(
                    EMAIL,
                    subject,
                    body
            );
            emailEventService.markSent(eventId);

        } catch (Exception e) {
            emailEventService.markFailed(
                    eventId,
                    e.getMessage()
            );
            throw e;
        }

    }

}
