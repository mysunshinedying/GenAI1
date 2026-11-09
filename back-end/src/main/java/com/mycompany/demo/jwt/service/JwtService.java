package com.mycompany.demo.jwt.service;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mycompany.demo.database.entity.Member;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService  {

    // JWT를 생성하거나 검증할 때 사용할 SecretKey
    private final SecretKey secretKey;

    // 문자열을 주입받아 SecretKey 생성 해당 문자열은 노출되지 않아야 하므로
    // 환경변수 값으로 대체한다
    public JwtService(@Value("${jwt.secret.key}") String jwtSecretKey) throws Exception {
        byte[] bytes = (jwtSecretKey).getBytes("UTF-8");
        // 32byte 이상의 길이를 가지고 있어야 한다
        secretKey = Keys.hmacShaKeyFor(bytes);
    }

    public String createJwt(Member member) {
        // 유효기간
        Date date = new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 365); // 1년 유효기간
        // JWT 생성

        String jwt = Jwts.builder()
                .subject(member.getMemberId())
                // 추가적으로 저장하고 싶은 키와 데이터 저장
                .claim("email", member.getEmail())
                .expiration(date)
                .signWith(secretKey)
                .compact();
        log.info("jwt: {}", jwt);

        return jwt;
    }

    public boolean validateJwt(String jwt) {
            // jwt parser 생성
            JwtParser jwtParser = Jwts.parser()
                    .verifyWith(secretKey)
                    .build();

            // JWT를 해석해서 claim을 생성
            Jws<Claims> jws = jwtParser.parseSignedClaims(jwt);

            return true;
        }

    public Map<String, String> getClaims(String jwt) {

        JwtParser jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();

        Jws<Claims> jws = jwtParser.parseSignedClaims(jwt);
        Claims claims = jws.getPayload();


        return Map.of(
            "memberId", claims.getSubject(),
            "email", claims.get("email").toString()
        );
    }
}
