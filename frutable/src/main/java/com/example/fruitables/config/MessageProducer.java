package com.example.fruitables.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOtpMessage(String email, String otpCode) {
        String message = "Email: " + email + ", Kod: " + otpCode;
        System.out.println("Mesaj göndərilir: " + message);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                message
        );
    }
}