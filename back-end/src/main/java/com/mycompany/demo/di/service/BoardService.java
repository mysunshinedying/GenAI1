package com.mycompany.demo.di.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
public class BoardService {

    public void write() {
        log.info("게시글 작성 처리");
    }
}
