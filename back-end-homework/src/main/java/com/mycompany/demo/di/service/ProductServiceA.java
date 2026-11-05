package com.mycompany.demo.di.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service 
@Primary
@Slf4j  
public class ProductServiceA implements IProductService {

    @Override
    public void productCreate() {
        log.info("Product created: Implementation A");        
    }

}
