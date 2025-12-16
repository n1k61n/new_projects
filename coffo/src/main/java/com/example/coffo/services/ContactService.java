package com.example.coffo.services;

import com.example.coffo.DTOs.request.ContactRequestDTO;
import com.example.coffo.DTOs.responce.ContactResponceDTO;

public interface ContactService {
    ContactResponceDTO saveContactMessage(ContactRequestDTO contactRequestDTO);
}