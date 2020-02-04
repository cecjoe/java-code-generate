package com.joe.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntryController {

    @RequestMapping("/index")
    public String index(){
        return "welcome";
    }
}
