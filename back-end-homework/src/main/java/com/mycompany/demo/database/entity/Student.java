package com.mycompany.demo.database.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private long studentId;
    private String studentNumber;
    private String studentName;
    private String email;
    private LocalDateTime createdAt;
}