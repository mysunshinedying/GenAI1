package com.mycompany.demo.di.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.di.dto.JoinRequest;
import com.mycompany.demo.di.service.AiService;
import com.mycompany.demo.di.service.BoardService;
import com.mycompany.demo.di.service.DiInterface;
import com.mycompany.demo.di.service.DiMemberService;

// DI: 생성자 주입
@RestController
@RequestMapping("/di2")
public class DiController2 {

    private final DiMemberService diMemberService;
    private final BoardService boardService;
    private final AiService aiService;
    private final DiInterface diInterface;

    public DiController2(
            DiMemberService diMemberService,
            BoardService boardService,
            AiService aiService,
            @Qualifier("diInterfaceImplB") DiInterface diInterface) {
        this.diMemberService = diMemberService;
        this.boardService = boardService;
        this.aiService = aiService;
        this.diInterface = diInterface;
    }

    @PostMapping("/join")
    public String join(JoinRequest joinRequest) {
        diMemberService.join();
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
