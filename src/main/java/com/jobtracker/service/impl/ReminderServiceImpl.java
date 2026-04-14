package com.jobtracker.service.impl;

import com.jobtracker.dto.ReminderDto;
import com.jobtracker.dto.ReminderRequest;
import com.jobtracker.entity.Reminder;
import com.jobtracker.entity.ReminderStatus;
import com.jobtracker.repository.ReminderRepository;
import com.jobtracker.service.EmailService;
import com.jobtracker.service.NotificationService;
import com.jobtracker.service.ReminderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ReminderServiceImpl implements ReminderService {
    private final ReminderRepository reminderRepository;
    private final ModelMapper mapper;
    private final NotificationService notificationService;
    private final EmailService emailService;

    @Override
    public ReminderDto createReminder(ReminderRequest reminderRequest) {
        var reminder=Reminder.builder()
                .jobId(reminderRequest.getJobId())
                .reminderTime(reminderRequest.getReminderTime())
                .type(reminderRequest.getType())
                .status(ReminderStatus.PENDING)
                .email(reminderRequest.getEmail())
                .build();
        Reminder savedReminder= reminderRepository.save(reminder);
        return mapper.map(savedReminder,ReminderDto.class);
    }

    public List<Reminder> getDueReminders(){
        List<Reminder> reminders= reminderRepository.findByReminderTimeBeforeAndStatus(OffsetDateTime.now(),ReminderStatus.PENDING);
        return reminders;
    }
    @Override
    public Optional<Reminder> getDueReminderByJobId(Long jobId){
        Optional<Reminder> reminders= reminderRepository.findByJobId(jobId);
        return reminders;
    }

    public void processReminders(){
        List<Reminder> reminderList=getDueReminders();

        for(Reminder reminder : reminderList){
            try {
                sendEmail(reminder);

                notificationService.sendReminder(reminder);
                reminder.setStatus(ReminderStatus.SENT);
            }catch(Exception e){
                handleFailure(reminder,e);
            }
            reminderRepository.save(reminder);
        }
    }

    private  void sendEmail(Reminder reminder) {
        if(null!=reminder && !StringUtils.isEmpty(reminder.getEmail())) {
            String subject = "Job Reminder ";
            String body = "Hi,\n\n"
                    + "This is a reminder for your job application.\n"
                    + "Job ID: " + reminder.getJobId() + "\n"
                    + "Type: " + reminder.getType() + "\n\n"
                    + "Best of luck! ";
            emailService.sendReminderEmail(reminder.getEmail(), subject, body);
        }

    }

    private void handleFailure(Reminder reminder,Exception e){
        int retryCount=reminder.getRetryCount()+1;
        reminder.setRetryCount(retryCount);
        if(retryCount>3){
            reminder.setStatus(ReminderStatus.FAILED);
        }else{
            reminder.setStatus(ReminderStatus.PENDING);
            reminder.setReminderTime(reminder.getReminderTime().plusMinutes(5));
        }
        log.info("Failed to process reminder id {} ",reminder.getId(),e);
    }

}
