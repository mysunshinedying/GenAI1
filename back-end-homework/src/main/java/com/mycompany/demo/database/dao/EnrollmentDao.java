package com.mycompany.demo.database.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.demo.database.entity.Enrollment;

@Mapper
public interface EnrollmentDao {
    @Insert("""
        INSERT INTO enrollment (student_id, course_id, status)
        VALUES (#{studentId}, #{courseId}, #{status})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "enrollmentId")
    int insert(Enrollment enrollment);

    @Select("""
        SELECT enrollment_id, student_id, course_id, status, enrolled_at
        FROM enrollment
        WHERE enrollment_id = #{enrollmentId}
        """)
    Enrollment selectById(@Param("enrollmentId") long enrollmentId);

    @Select("""
        SELECT enrollment_id, student_id, course_id, status, enrolled_at
        FROM enrollment
        ORDER BY enrollment_id
        """)
    List<Enrollment> selectAll();

    @Update("""
        UPDATE enrollment
        SET student_id = #{studentId},
        course_id = #{courseId},
        status = #{status}
        WHERE enrollment_id = #{enrollmentId}
        """)
    int update(Enrollment enrollment);

    @Delete("DELETE FROM enrollment WHERE enrollment_id = #{enrollmentId}")
    int deleteById(@Param("enrollmentId") long enrollmentId);
}