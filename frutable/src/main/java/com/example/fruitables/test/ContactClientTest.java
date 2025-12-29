package com.example.fruitables.test;

import com.example.fruitables.dtos.contact.ContactDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "contact-service-test", url = "http://localhost:8083")
public interface ContactClientTest {
    @GetMapping("/api/contacts/unread")
    List<ContactDto> getUnreadMessages();
}