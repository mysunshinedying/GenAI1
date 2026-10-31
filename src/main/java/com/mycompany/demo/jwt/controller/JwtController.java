package com.mycompany.demo.jwt.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.database.entity.Member;
import com.mycompany.demo.jwt.dto.LoginResponse;
import com.mycompany.demo.jwt.dto.OrderRequest;
import com.mycompany.demo.jwt.dto.OrderResponse;
// import com.mycompany.demo.jwt.service.JwtMemberService;
import com.mycompany.demo.jwt.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@RestController 
@RequestMapping("/jwt")
@Slf4j 
 
public class JwtController {

    // private final JwtMemberService memberService;
    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
    public LoginResponse login() {
        Member member = new Member();

        member.setMemberId("user1");
        member.setEmail("user1@mycompany.com");
        String jwt = jwtService.createJwt(member);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatus("success");
        loginResponse.setJwt(jwt);

        return loginResponse;
    }

    @PostMapping("/order")
    public OrderResponse order(@RequestBody OrderRequest orderRequest) {
        String jwt = orderRequest.getJwt();

        try {
            jwtService.validateJwt(jwt);
            Map<String, String> map = jwtService.getClaims(jwt);
            String memberId = map.get("memberId");
            String email = map.get("email");

            log.info("memberId: {}, email: {}", memberId, email);

            return new OrderResponse("success", jwt);
        } catch (MalformedJwtException e) {
            return new OrderResponse("fail", "토큰의 형식이 맞지 않습니다");
        } catch (SignatureException e) {
            return new OrderResponse("fail", "유효한 토큰이 아닙니다.");
        } catch (ExpiredJwtException e) {
            return new OrderResponse("fail", "유효 기간이 만료된 토큰입니다.");
        }

    }

}
