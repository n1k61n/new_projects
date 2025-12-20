package com.example.fruitables.services;

import com.example.fruitables.dtos.contact.ContactMessageCreateDto;
import com.example.fruitables.dtos.contact.ContactMessageDto;
import com.example.fruitables.dtos.contact.ContactMessageReadDto;
import com.example.fruitables.models.ContactMessage;

import java.util.List;

public interface ContactMessageService {
    void createMessage(ContactMessageCreateDto contactMessageCreateDto);

    List<ContactMessageReadDto> getContactMessages();

    void markAsRead(Long id);

    void sendEmailToAdmin(ContactMessageDto message);


    ContactMessageDto findByIdAndMarkAsRead(Long id);
}
