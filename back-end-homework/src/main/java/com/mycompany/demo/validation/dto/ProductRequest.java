package com.mycompany.demo.validation.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ProductRequest {
    @NotBlank(message = "상품의 이름은 필수입니다.")
    @Size (min = 2, max = 50, message = "상품의 이름은 2자 이상 50자 이하이어야 합니다.")
    private String name;

    @Min (value = 100, message = "상품의 가격은100원 이상이어야 합니다.")
    @Max (value = 10_000_000, message = "상품의 가격은 10,000,000원 이하이어야 합니다.")
    private int price;
    
    private String description;

    @Min (value = 1, message = "상품의 재고는 1 이상이어야 합니다.")
    private int stock;
}