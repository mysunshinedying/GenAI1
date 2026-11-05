package com.mycompany.demo.database.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewWriteRequest {
    private String title;
    private String content;
    private String writer;    
    private MultipartFile file;
    private Long productId;
}
