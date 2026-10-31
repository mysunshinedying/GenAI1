package com.mycompany.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.demo.DTO.BoardRequest;
import com.mycompany.demo.DTO.BoardResponse;
import com.mycompany.demo.DTO.MemberRequest;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController 
@Slf4j 
@RequestMapping ("/api")
public class ApiController {

    @GetMapping(value = "/method1", produces = "text/plain")
    public String method1(
        @RequestParam("param1") String param1,
        @RequestParam("param2") String param2
    ) {
        log.info("param1: {}, param2: {}", param1, param2);
        return "응답내용: " + param1 + param2;
    }

    @PostMapping(value = "/method2",
                produces = "text/plain",
                consumes = "application/x-www-form-urlencoded")
    public String method2(
        @RequestParam("param1") String param1,
        @RequestParam("param2") String param2
    ) {
        log.info("param1: {}, param2: {}", param1, param2);
        return "응답내용2: " + param1 + param2;
    }

    @PutMapping(value = "/method3",
                produces = "text/plain",
                consumes = "multipart/form-data")
    public String method3(
        @RequestParam("param1") String param1,
        @RequestParam("param2") String param2,
        @RequestParam("param3") MultipartFile param3
    ) {
        log.info("param1: {}, param2: {}, param3: {}", param1, param2, param3.getOriginalFilename());
        return "응답내용3: " + param1 + param2 + param3.getOriginalFilename();
    }

    @DeleteMapping(value = "/method4",
                produces = "text/plain",
                consumes = "application/json"
    )
    public String method4(@RequestBody Method4Request request) {
        log.info("param1: {}, param2: {}", request.param1(), request.param2());
        return "응답내용3";
    }

    public record Method4Request(String param1, int param2) {}

    @PatchMapping(value = "/method5/{bno}",
                produces = "text/plain"
    )
    public String method5(
        @PathVariable("bno") String bno
    ) {
        log.info("bno: {}", bno);
        return "bno: " + bno;
    }

    @PostMapping(value = "/method6",
                produces = "text/plain",
                consumes = "application/json"
    )
    public String method6(@RequestBody MemberRequest member) {
        log.info("userId: {}, email: {}, password: {}, hobbies: {}, job: {}, gender: {}",
                member.getUserId(), member.getEmail(), member.getPassword(), member.getHobbies(), member.getJob(), member.getGender());
        return "회원 가입 완료: " + member.getUserId();
    }

    @PostMapping(value = "/method7",
                produces = "application/json",
                consumes = "multipart/form-data"
    )
    public ResponseEntity<BoardResponse> method7(@ModelAttribute BoardRequest request) {
        String attachFileName = request.getAttach() != null ? request.getAttach().getOriginalFilename() : null;
        log.info("bno: {}, title: {}, content: {}, writer: {}, attach: {}",
                request.getBno(), request.getTitle(), request.getContent(), request.getWriter(), attachFileName);

        BoardResponse response = new BoardResponse();
        response.setBno(request.getBno());
        response.setTitle(request.getTitle());
        response.setContent(request.getContent());
        response.setWriter(request.getWriter());
        response.setAttachFileName(attachFileName);
        return ResponseEntity.ok(response);
    }

}
