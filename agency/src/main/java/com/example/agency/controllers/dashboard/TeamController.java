package com.example.agency.controllers.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/teams")
public class TeamController {


    @GetMapping("/index")
    public String index(){
        return "dashboard/teams/index";
    }
}
