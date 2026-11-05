package com.mycompany.demo.httpmethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/httpmethod")
public class httpMethodController {

    @GetMapping("/info")
    public String info() {
        log.info("info 실행");
        // log.warn("info warn");
        // log.error("info error");
        // log.debug("info debug");
        return "정보 조회";
    }

    @GetMapping("/method1")
    public String method1() {
        log.info("method1 실행");
        return "메서드1 실행";
    }
    

    @PostMapping("/method2")
    public String method2() {
        log.info("method2 실행");
        return "메서드2 실행";
    }    

    @PutMapping("/method3")
    public String method3() {
        log.info("method3 실행");
        return "메서드3 실행";
    }        


    @DeleteMapping("/method4")
    public String method4() {
        log.info("method4 실행");
        return "메서드4 실행";
    }          

}
