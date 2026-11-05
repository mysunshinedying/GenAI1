package com.mycompany.demo.database.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private long courseId;
    private long instructorId;
    private String courseCode;
    private String courseName;
    private String description;
    private int capacity;
    private LocalDateTime createdAt;
}