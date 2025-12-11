package com.example.coffo.services;

import com.example.coffo.DTOs.responce.ContactResponceDTO;

public interface ContactService {
    ContactResponceDTO getContactById(Long id);
    ContactResponceDTO createContact(ContactResponceDTO contactResponceDTO);
    ContactResponceDTO updateContact(Long id, ContactResponceDTO contactResponceDTO);
    boolean deleteContact(Long id);
}
