package com.mycompany.demo.database.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class ReviewDeleteResponse {
    private String status;
    private String message;
}
