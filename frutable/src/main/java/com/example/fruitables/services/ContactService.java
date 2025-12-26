package com.example.fruitables.services;

import com.example.fruitables.dtos.contact.ContactDto;

public interface ContactService {
    boolean createContact(ContactDto contactDto);
}
