package com.mycompany.demo.database.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Board;
import java.util.List;
import java.util.Map;

@Mapper 
public interface BoardDao {
    int insert(Board board);

    Board selectById(long boardId);

    int selectCount(@Param("where") Map<String, Object> where);

    List<Board> selectPage(@Param("pager") Pager pager, @Param("where") Map<String, Object> where);

    int update(Board board);

    int deleteById(long boardId);
}
