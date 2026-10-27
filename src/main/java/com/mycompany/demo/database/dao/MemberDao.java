package com.mycompany.demo.database.dao;
import com.mycompany.demo.database.dto.Pager;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.demo.database.entity.Member;

@Mapper
public interface MemberDao {
    int insert(Member member);

    Member selectById(@Param("memberId") String memberId);

    // 행수를 반환하는 메서드
    int selectCount(@Param("where") Map<String, Object> where);

    List<Member> selectPage(@Param("pager") Pager pager, @Param("where") Map<String, Object> where);

    int update(Member member);

    int deleteById(@Param("memberId") String memberId);


}