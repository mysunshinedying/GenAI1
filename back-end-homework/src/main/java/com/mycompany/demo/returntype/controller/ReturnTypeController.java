package com.mycompany.demo.returntype.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.returntype.dto.Product;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/returntype")
@Slf4j
public class ReturnTypeController {

    // 단순 문자열을 return한다
    @GetMapping("/string")
    public String getString() {
        return "Hello, World!";
    }

    // DTO 객체를 XML과 JSON으로 return 한다
    @GetMapping(value = "dto", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Product getDto() {
        return new Product("Sample Product", 4.5, 22000.0, 5);
    }

    // List 형태로 반환하기
    @GetMapping("/list")
    public List<Product> getList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Sample Product 1", 4.5, 22000.0, 5));
        products.add(new Product("Sample Product 2", 4.0, 18000.0, 10));
        return products;
    }

    // map 형태로 List 반환하기
    @GetMapping("/map")
    public Map<String, Product> getMap() {
        Map<String, Product> productMap = new HashMap<>();
        productMap.put("product1", new Product("Sample Product 1", 4.5, 22000.0, 5));
        productMap.put("product2", new Product("Sample Product 2", 4.0, 18000.0, 10));
        return productMap;
    }

    // ResponseEntity를 사용하여 반환하기
    @GetMapping("/response-entity")
    public ResponseEntity<Product> getResponseEntity() {
        Product product = new Product("Sample Product", 4.5, 22000.0, 5);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    // HttpEntity를 사용하여 반환하기
    @GetMapping("/http-entity")
    public HttpEntity<Product> getHttpEntity() {
        Product product = new Product("Sample Product", 4.5, 22000.0, 5);
        return new HttpEntity<>(product);
    }

    // Void로 download 로직 구현
    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        log.info("Download request received");

        String filePath = "C:/gen-ai-course/data/image/generatedImage-4-1536x1024.png";
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=\"generatedImage-4-1536x1024.png\"");

        log.info("Preparing to send file: {}", filePath);

        int chunkSize = 1024;

        byte[] buffer = new byte[chunkSize];
        
        try {
            FileInputStream fis = new FileInputStream(filePath);
            OutputStream os = response.getOutputStream();

            while(true) {
                int bytesRead = fis.read(buffer);
                if (bytesRead == -1) {
                    break;
                }
                os.write(buffer, 0, bytesRead);
            }
            fis.close();
            os.flush();
            log.info("download success.");            
        } catch (IOException e) {
            log.error("Error occurred during download", e);
        }
        }

    }

