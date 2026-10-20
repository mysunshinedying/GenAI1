package com.mycompany.demo.returntype.controller;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.returntype.dto.Board;
import com.mycompany.demo.returntype.dto.Gender;
import com.mycompany.demo.returntype.dto.Member;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/returntype")
@Slf4j
public class ReturnTypeController {
    @GetMapping("/string")
    public String getString() {
        return "문자열 결과";
    }

    @GetMapping(value = "/dto", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public Board getDto() {
        Board board = new Board(5, "제목", "내용", "작성자", new Date());
        return board;
    }

    @GetMapping(value = "/dto2", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public Member getDto2() {
        Member member = new Member("12340", "홍길동", "1234@mycompany.com", "5678910", Gender.MALE, "010-1234-5678",
                List.of("Java", "Spring"), new Date());
        return member;
    }

    @GetMapping(value = "/list")
    public List<Board> getList() {
        List<Board> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(new Board(i, "제목" + i, "내용" + i, "홍길동", new Date()));
        }

        return list;
    }

    @GetMapping(value = "/map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("bno", 1);
        map.put("btitle", "제목");
        map.put("bcontent", "내용");
        map.put("bwriter", "홍길동");
        map.put("bdate", new Date());
        return map;
    }

    @GetMapping(value = "/response-entity")
    public ResponseEntity<Board> getResponseEntity() {
        Board board = new Board(5, "제목", "내용", "작성자", new Date());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .header("my-header", "my-value")
                .body(board);
    }

    @GetMapping(value = "/http-entity")
    public HttpEntity<Board> getHttpEntity() {
        Board board = new Board(5, "제목", "내용", "작성자", new Date());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("my-header", "my-value");
        HttpEntity<Board> httpEntity = new HttpEntity<>(board, headers);
        return httpEntity;
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        log.info("download start...");


        // 파일 경로
        String filePath = "C:/gen-ai-course/data/image/generatedImage-4-1536x1024.png";        

        // 응답 헤더 추가
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=\"generatedImage-4-1536x1024.png\"");

        log.info("preparing download...");        

        // 청크 단위 다운로드 설정(읽고 응답 본문에 출력하는 단위)
        int chunkSize = 1024; // 예시로 1KB씩 다운로드

        // 데이터 버퍼 (1KB에 해당되는 데이터가 저장되는 배열)
        byte[] data = new byte[chunkSize];

        try {
            // 입력 스트림 얻기
            FileInputStream fis = new FileInputStream(filePath);

            // 출력 스트림 얻기(응답 본문에 출력)
            OutputStream os = response.getOutputStream();

            // 청크 크기 만큼 읽고 출력을 반복
            while(true) {
                // 최대로 청크 크기만큼 읽고, 실제로 읽은 바이트 수를 반환
                // 읽은 데이터는 데이터 버퍼 저장
                int bytesRead = fis.read(data);

                // 읽은 데이터를 본문에 출력
                if (bytesRead == -1) break;
                os.write(data, 0, bytesRead);
                os.flush(); // 출력 스트림을 비워서 클라이언트로 전송
            }
            os.close();
            fis.close();
            log.info("download success.");
        } catch (Exception e) {
            log.error("Error occurred during download", e);
        }

    }

}