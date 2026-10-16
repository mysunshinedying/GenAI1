package com.mycompany.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponse {
    private int bno;
    private String title;
    private String content;
    private String writer;
    private String attachFileName;
}
