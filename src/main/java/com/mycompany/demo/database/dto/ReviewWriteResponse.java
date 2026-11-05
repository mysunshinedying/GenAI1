package com.mycompany.demo.database.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class ReviewWriteResponse {
    private String status;
    private String message;
}
