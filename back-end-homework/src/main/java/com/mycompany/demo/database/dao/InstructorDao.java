package com.mycompany.demo.database.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.demo.database.entity.Instructor;

@Mapper
public interface InstructorDao {
    @Insert("""
        INSERT INTO instructor (instructor_name, email)
        VALUES (#{instructorName}, #{email})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "instructorId")
    int insert(Instructor instructor);

    @Select("""
        SELECT instructor_id, instructor_name, email, created_at
        FROM instructor
        WHERE instructor_id = #{instructorId}
        """)
    Instructor selectById(@Param("instructorId") long instructorId);

    @Select("""
        SELECT instructor_id, instructor_name, email, created_at
        FROM instructor
        ORDER BY instructor_id
        """)
    List<Instructor> selectAll();

    @Update("""
        UPDATE instructor
        SET instructor_name = #{instructorName},
        email = #{email}
        WHERE instructor_id = #{instructorId}
        """)
    int update(Instructor instructor);

    @Delete("DELETE FROM instructor WHERE instructor_id = #{instructorId}")
    int deleteById(@Param("instructorId") long instructorId);
}