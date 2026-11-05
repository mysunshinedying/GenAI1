package com.mycompany.demo.receivedata.dto;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Product {
    private int pno;
    private String name;    
    private double rating;
    private double price;
    private int stock;  
    private String description;
    MultipartFile image;
}
