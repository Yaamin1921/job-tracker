package com.jobtracker.service;

public interface EmailService {
    void sendReminderEmail(String to, String subject, String body);

}
