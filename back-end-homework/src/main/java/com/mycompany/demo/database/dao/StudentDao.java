package com.mycompany.demo.database.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.demo.database.entity.Student;

@Mapper
public interface StudentDao {
    @Insert("""
        INSERT INTO student (student_number, student_name, email)
        VALUES (#{studentNumber}, #{studentName}, #{email})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "studentId")
    int insert(Student student);

    @Select("""
        SELECT student_id, student_number, student_name, email, created_at
        FROM student
        WHERE student_id = #{studentId}
        """)
    Student selectById(@Param("studentId") long studentId);

    @Select("""
        SELECT student_id, student_number, student_name, email, created_at
        FROM student
        ORDER BY student_id
        """)
    List<Student> selectAll();

    @Update("""
        UPDATE student
        SET student_number = #{studentNumber},
        student_name = #{studentName},
        email = #{email}
        WHERE student_id = #{studentId}
        """)
    int update(Student student);

    @Delete("DELETE FROM student WHERE student_id = #{studentId}")
    int deleteById(@Param("studentId") long studentId);
}