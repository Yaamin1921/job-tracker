package com.jobtracker.service.impl;

import com.jobtracker.dto.ReminderDto;
import com.jobtracker.dto.ReminderRequest;
import com.jobtracker.entity.Reminder;
import com.jobtracker.entity.ReminderStatus;
import com.jobtracker.entity.ReminderType;
import com.jobtracker.repository.ReminderRepository;
import com.jobtracker.service.ReminderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReminderServiceImpl implements ReminderService {
    private final ReminderRepository reminderRepository;
    private final ModelMapper mapper;

    @Override
    public ReminderDto createReminder(ReminderRequest reminderRequest) {
        var reminder=Reminder.builder().jobId(reminderRequest.getJobId())
                .reminderTime(reminderRequest.getReminderTime())
                .type(reminderRequest.getType())
                .status(ReminderStatus.PENDING)
                .build();
        Reminder savedReminder= reminderRepository.save(reminder);
        return mapper.map(savedReminder,ReminderDto.class);
    }

}
