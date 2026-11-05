package com.mycompany.demo.di.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

import com.mycompany.demo.di.dto.ProductRequest;
import com.mycompany.demo.di.service.IProductService;

import lombok.RequiredArgsConstructor;

@RestController 
@Slf4j
@RequiredArgsConstructor 
@RequestMapping ("/di")
public class DiController {
    // Primary 통한 의존성 주입(productServiceA)
    private final IProductService productService;

    @PostMapping("/create")
    public String createProduct(@RequestBody ProductRequest productRequest) {
        log.info("Creating product with request: {}", productRequest);
        productService.productCreate();
        return "Product 생성됨";
    }
}
