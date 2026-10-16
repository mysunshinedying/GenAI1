package com.mycompany.demo.DTO;
import java.util.List;

import lombok.Data;

@Data
public class MemberRequest {
    private String userId;
    private String email;
    private String password;
    private List<String> hobbies;
    private String job;
    private String gender;
}