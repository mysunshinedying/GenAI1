package com.mycompany.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController 
@Slf4j 
@RequestMapping ("/api")
public class ApiController {

    @GetMapping("/method1")
    public String method1() {
        log.info("method1 호출됨");
        return "응답내용";
    }

    @PostMapping("/method2")
    public String method2() {
        log.info("method2 호출됨");
        return "응답내용2";
    }

    @PutMapping("/method3")
    public String method3() {
        log.info("method3 호출됨");
        return "응답내용3";
    }

    @DeleteMapping("/method4")
    public String method4() {
        log.info("method4 호출됨");
        return "응답내용4";
    }

}
