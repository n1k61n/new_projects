package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.contact.ContactDto;
import com.example.fruitables.services.ContactService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;

    @GetMapping("/contact")
    public String contact(Model model, Principal principal){
        if(principal != null) {
            ContactDto contactDto = userService.getContact(principal.getName());
            model.addAttribute("contact", contactDto);
        }
        else{
            model.addAttribute("contact", new ContactDto());
        }
        return "contact";
    }

    @PostMapping("/contact")
    public String createMessage(@ModelAttribute("contact")ContactDto contactDto, RedirectAttributes redirectAttributes) {
        boolean result = contactService.createContact(contactDto);
        redirectAttributes.addFlashAttribute("success", result);
        return "redirect:/contact";
    }

}
