package com.example.fruitables.services.impl;

import com.example.fruitables.config.RabbitMQConfig;
import com.example.fruitables.dtos.contact.ContactDto;
import com.example.fruitables.models.Contact;
import com.example.fruitables.repositories.ContactRepository;
import com.example.fruitables.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public boolean createContact(ContactDto contactDto) {
        try {
            Contact contact = modelMapper.map(contactDto, Contact.class);
            contact.setCreatedAt(LocalDateTime.now());
            contactRepository.save(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void saveAndNotify(Contact contact) {
        // 1. Bazaya yaz
        contactRepository.save(contact);

        // 2. RabbitMQ-ya bildiriş göndər
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE, "Yeni mesaj: " + contact.getName());
    }

}
