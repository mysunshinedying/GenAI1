package com.mycompany.demo.di.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j  
public class ProductServiceB implements IProductService {
    @Override
    public void productCreate() {
        log.info("Product created: Implementation B");
    }
}
