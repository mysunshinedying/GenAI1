package com.mycompany.demo.database.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    private long enrollmentId;
    private long studentId;
    private long courseId;
    private String status;
    private LocalDateTime enrolledAt;
}