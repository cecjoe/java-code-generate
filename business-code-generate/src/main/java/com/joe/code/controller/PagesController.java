package com.joe.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 所有的也米阿尼配置
 */
@Controller
public class PagesController {

    @RequestMapping("/project.cp")
    public String project(){
        return "project/manage";
    }

    @RequestMapping("/generate.cp")
    public String generate(){

        return "generate/manage";
    }

    @RequestMapping("/table_relate.cp")
    public String tableRelate(){

        return "generate/table_relate";
    }

    @RequestMapping("/export_code.cp")
    public String exportCode(){

        return "generate/export_code";
    }

}
