package com.example.agency.controllers.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/messages")
public class MessagesController {


    @GetMapping(path = {"", "/index"})
    public String index(){
        return "dashboard/messages/index";
    }


}