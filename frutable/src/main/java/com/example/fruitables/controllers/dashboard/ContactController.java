package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.contact.MessageCreateDto;
import com.example.fruitables.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final MessageService messageService;



    @PostMapping("/contact")
    public String createMessage(@ModelAttribute MessageCreateDto messageCreateDto) {
        try {
            messageService.createMessage(messageCreateDto);
            return "redirect:/contact?message=true";
        } catch (Exception e) {
            return "redirect:/contact?message=false";
        }
    }



}
