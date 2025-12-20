package com.example.fruitables.services;

import com.example.fruitables.dtos.contact.ContactMessageCreateDto;

public interface ContactMessageService {
    void createMessage(ContactMessageCreateDto contactMessageCreateDto);
}
