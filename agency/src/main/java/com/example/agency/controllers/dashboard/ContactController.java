package com.example.agency.controllers.dashboard;

import com.example.agency.dtos.contact.ContactCreateDTO;
import com.example.agency.dtos.contact.ContactDTO;
import com.example.agency.dtos.contact.ContactUpdateDTO;
import com.example.agency.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping(path = {"/index", "contacts"})
    public String contacts(Model model) {
        List<ContactDTO> contactDTOList = contactService.getAllContacts();
        model.addAttribute("contacts", contactDTOList);
        return "dashboard/contact/index";
    }

    @GetMapping("/create.html")
    public String create() {
        return "dashboard/contact/create";
    }

    @PostMapping("/create.html")
    public String create(ContactCreateDTO contactCreateDTO) {
        boolean result = contactService.createContact(contactCreateDTO);
        return "redirect:/dashboard/contact/contacts";
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        ContactUpdateDTO contactUpdateDTO = contactService.getUpdateContact(id);
        model.addAttribute("contact", contactUpdateDTO);
        return "dashboard/contact/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, ContactUpdateDTO contactUpdateDTO) {
        boolean result = contactService.updateContact(id, contactUpdateDTO);
        return "redirect:/dashboard/contact/contacts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boolean result = contactService.deleteContact(id);
        return "redirect:/dashboard/contact/contacts";
    }
}

