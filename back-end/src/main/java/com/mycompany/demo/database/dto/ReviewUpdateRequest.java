package com.mycompany.demo.database.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateRequest {
    private long reviewId;
    private String title;
    private String content;
    private MultipartFile file;
    private boolean deleteFile;
}
