package com.mycompany.demo.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private String memberId;
    private String memberName;
    private String password;
    private String email;
}
