package com.mycompany.demo.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data 
public class BoardRequest {
    private int bno;
    private String title;
    private String content;
    private String writer;
    private MultipartFile attach;
}
