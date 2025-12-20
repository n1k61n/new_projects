package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.category.CategoryDto;
import com.example.fruitables.dtos.contact.ContactMessageCreateDto;
import com.example.fruitables.dtos.contact.ContactMessageReadDto;
import com.example.fruitables.services.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactMessageService contactMessageService;

    @GetMapping("/messages/read/{id}")
    public String readMessage(@PathVariable Long id) {
        contactMessageService.markAsRead(id); // Mesajı bazada oxundu edirik
        return "redirect:/dashboard"; // Səhifəni yeniləyirik ki, say azalsın
    }

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
