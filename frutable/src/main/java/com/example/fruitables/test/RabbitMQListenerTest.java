package com.example.fruitables.test;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListenerTest {

    @RabbitListener(queues = "contact_queue")
    public void handleMessage(String message) {
        System.out.println("Bildiriş alındı: " + message);
        // Burada WebSocket ilə brauzerə anlıq bildiriş göndərə bilərsiniz
    }
}
