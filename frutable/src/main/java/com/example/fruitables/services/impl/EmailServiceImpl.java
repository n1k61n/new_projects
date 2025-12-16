package com.example.fruitables.services.impl;

import com.example.fruitables.services.EmailService;
import org.springframework.mail.SimpleMailMessage;

public class EmailServiceImpl implements EmailService {
    @Override
    public void sendSimplMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
