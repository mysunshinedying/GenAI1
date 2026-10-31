package com.mycompany.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;


@Controller 
@Slf4j 
public class UIController {
    @RequestMapping("/")
    public String index() {
        log.info("index() 실행");
        return "index";
    }

    @RequestMapping("/exam01-variables")
    public String exam01Variables() {
        log.info("exam01Variables() 실행");
        return "basic/exam01-variables";
    }

    @RequestMapping("/exam02-function")
    public String exam02Function() {
        return "basic/exam02-function";
    }

    @RequestMapping("/exam03-object")
    public String exam03Object() {
        return "basic/exam03-object";
    }

    @RequestMapping("/exam04-array")
    public String exam04Array() {
        return "basic/exam04-array";
    }

    @RequestMapping("/exam05-dom")
    public String exam05Dom() {
        return "basic/exam05-dom";
    }

    @RequestMapping("/exam06-form")
    public String exam06Form() {
        return "basic/exam06-form";
    }

    @RequestMapping("/exam07-fetch")
    public String exam07Fetch() {
        return "basic/exam07-fetch";
    }

    @RequestMapping("/exam08-fetch-form")
    public String exam08FetchForm() {
        return "basic/exam08-fetch-form";
    }

    @RequestMapping("/exam09-fetch-form")
    public String exam09FetchForm() {
        return "basic/exam09-fetch-form";
    }
}
