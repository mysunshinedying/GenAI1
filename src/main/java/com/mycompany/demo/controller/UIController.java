package com.mycompany.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UIController {

    @RequestMapping("/") 
    public String index() {
        return "index";
    }

    @RequestMapping("/exam01-variables")
    public String exam01Variables() {
        return "basic/exam01-variables";
    }
    
}
