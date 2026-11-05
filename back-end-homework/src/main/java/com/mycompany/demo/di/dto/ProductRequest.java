package com.mycompany.demo.di.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ProductRequest {
    private String name;
    private int price;
    private String description;
    private int stock;
}