package com.mycompany.demo.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class MemberLoginResponse {
    private String status;
    private String message;
    private String memberId;
    private String jwt;
}
