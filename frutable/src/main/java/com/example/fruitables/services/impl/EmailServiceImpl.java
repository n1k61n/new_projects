package com.example.fruitables.services.impl;

import com.example.fruitables.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final MailSender mailSender;

    @Override
    public void sendEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");

//        String body = "Salam hesabi aktivlesdirmek ucun bu linke klikleyin " + "http://localhost:8080/confirm?token=" + token;
        String body = "Salam hesabi aktivlesdirmek ucun bu linke klikleyin " + token;
        message.setTo(to);
        message.setSubject("Hesabın Təsdiqlənməsi");
        message.setText(body);
        mailSender.send(message);
    }
}
