package com.mycompany.demo.returntype.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Member {
    private String id;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private String phoneNumbers;
    private List<String> skills;
    private Date joinDate;    
}
