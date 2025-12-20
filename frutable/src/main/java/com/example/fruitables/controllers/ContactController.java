package com.example.fruitables.controllers;

import com.example.fruitables.dtos.contact.ContactMessageCreateDto;
import com.example.fruitables.services.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactMessageService contactMessageService;


    @PostMapping("/contact")
    public String createMessage(@ModelAttribute ContactMessageCreateDto contactMessageCreateDto) {
        try {
            contactMessageService.createMessage(contactMessageCreateDto);
            return "redirect:/contact?message=true";
        } catch (Exception e) {
            return "redirect:/contact?message=false";
        }
    }



}
