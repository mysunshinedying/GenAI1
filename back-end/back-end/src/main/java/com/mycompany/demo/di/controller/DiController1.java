package com.mycompany.demo.di.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.di.dto.JoinRequest;
import com.mycompany.demo.di.service.AiService;
import com.mycompany.demo.di.service.BoardService;
import com.mycompany.demo.di.service.DiInterface;
import com.mycompany.demo.di.service.MemberService;

// DI: 필드 주입 - Legacy 방식
@RestController
@RequestMapping("/di1")
public class DiController1 {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private AiService aiService;

    @Autowired
    @Qualifier("diA")
    private DiInterface diInterface;

    @PostMapping("/join")
    public String join(JoinRequest joinRequest) {
        memberService.join();
        return "회원가입";
    }

    @PostMapping("/board-write")
    public String boardWrite() {
        boardService.write();
        return "게시글 작성";
    }

    @GetMapping("/chat")
    public String chat() {
        aiService.chat();
        return "채팅";
    }

    @GetMapping("/qualifier")
    public String interfac() {
        diInterface.method();
        return "실행 결과";
    }
}