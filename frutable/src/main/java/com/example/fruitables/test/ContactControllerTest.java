package com.example.fruitables.test;

import com.example.fruitables.models.Contact;
import com.example.fruitables.payloads.results.Result;
import com.example.fruitables.payloads.results.success.SuccessResult;
import com.example.fruitables.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactControllerTest {
    private final ContactRepository repository;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public ResponseEntity<Result> sendMessage(@RequestBody Contact contact) {
        contact.setCreatedAt(LocalDateTime.now());
        repository.save(contact);

        // Yeni mesaj gəldiyini Dashboard-a bildirmək üçün RabbitMQ-ya mesaj atırıq
        rabbitTemplate.convertAndSend("contactExchange", "contactRoutingKey", "Yeni mesaj var!");

        return ResponseEntity.ok(new SuccessResult(true, "Mesajınız göndərildi!"));
    }
}