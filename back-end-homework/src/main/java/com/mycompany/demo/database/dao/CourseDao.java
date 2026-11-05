package com.mycompany.demo.database.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.demo.database.entity.Course;

@Mapper
public interface CourseDao {
    @Insert("""
        INSERT INTO course (instructor_id, course_code, course_name, description, capacity)
        VALUES (#{instructorId}, #{courseCode}, #{courseName}, #{description}, #{capacity})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "courseId")
    int insert(Course course);

    @Select("""
        SELECT course_id, instructor_id, course_code, course_name, description, capacity, created_at
        FROM course
        WHERE course_id = #{courseId}
        """)
    Course selectById(@Param("courseId") long courseId);

    @Select("""
        SELECT course_id, instructor_id, course_code, course_name, description, capacity, created_at
        FROM course
        ORDER BY course_id
        """)
    List<Course> selectAll();

    @Update("""
        UPDATE course
        SET instructor_id = #{instructorId},
        course_code = #{courseCode},
        course_name = #{courseName},
        description = #{description},
        capacity = #{capacity}
        WHERE course_id = #{courseId}
        """)
    int update(Course course);

    @Delete("DELETE FROM course WHERE course_id = #{courseId}")
    int deleteById(@Param("courseId") long courseId);
}