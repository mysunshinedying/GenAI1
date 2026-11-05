package com.mycompany.demo.database.dto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class ReviewResponse {
    private long reviewId;
    private String title;
    private String content;
    private String fileName;
    private String fileType;
    private byte[] fileData;
    private String writer;
    private Date writtenAt;
    private long productId;
}
