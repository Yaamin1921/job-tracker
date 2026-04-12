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
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
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

    public void processReminders(){
        List<Reminder> reminderList=getDueReminders();

        for(Reminder reminder : reminderList){
            sendEmail(reminder);

            notificationService.sendReminder(reminder);
            reminder.setStatus(ReminderStatus.SENT);
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

}
