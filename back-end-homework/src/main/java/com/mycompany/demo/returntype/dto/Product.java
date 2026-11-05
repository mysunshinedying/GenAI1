package com.mycompany.demo.returntype.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Product {
    private String name;    
    private double rating;
    private double price;
    private int stock;  
}
