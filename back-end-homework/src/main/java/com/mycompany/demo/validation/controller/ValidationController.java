package com.mycompany.demo.validation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.validation.dto.ProductRequest;

import jakarta.validation.Valid;


@RestController 
@RequestMapping ("/validation")
public class ValidationController {
    @PostMapping("/create")
    public String createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return "Product 정상 생성됨";
    }
}
