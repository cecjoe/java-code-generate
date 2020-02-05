package com.joe.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntryController {

    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "welcome";
    }
}
