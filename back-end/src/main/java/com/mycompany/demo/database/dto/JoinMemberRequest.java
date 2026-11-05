package com.mycompany.demo.database.dto;

import com.mycompany.demo.database.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinMemberRequest {
    private String memberId;
    private String memberName;
    private String password;
    private String email;

//    public Member toEntity(JoinMemberRequest joinMemberRequest) {
//        return new Member(
//                joinMemberRequest.getMemberId(),
//                joinMemberRequest.getMemberName(),
//                joinMemberRequest.getPassword(),
//                joinMemberRequest.getEmail(),
//                null
//        );
//    }
}
