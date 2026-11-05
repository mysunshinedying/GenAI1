package com.mycompany.demo.validation.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.demo.validation.dto.JoinRequest;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/validation")
@Slf4j
public class ValidationController {


    public ValidationController() {
        log.info("initialized ValidationController");
    }

    @PostMapping("/join")
    public String join(@Valid JoinRequest joinRequest) {
        log.info("JoinRequest: {}", joinRequest.toString());
        return "회원가입";
    }
    
}
