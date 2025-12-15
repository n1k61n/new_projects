package com.example.coffo.controllers;

import com.example.coffo.DTOs.request.ContactRequestDTO;
import com.example.coffo.repositories.ContactRepository;
import com.example.coffo.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public  String createContact( ContactRequestDTO contactRequestDTO){
        contactService.saveContactMessage(contactRequestDTO);
        return "redirect:/index";
    }

}
