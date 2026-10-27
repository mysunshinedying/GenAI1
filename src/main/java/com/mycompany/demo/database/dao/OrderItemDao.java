package com.mycompany.demo.database.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.OrderItem;

@Mapper
public interface OrderItemDao {
    int insert(OrderItem orderItem);

    OrderItem selectById(OrderItem orderItem);

    int selectCount(@Param("where") Map<String, Object> where);

    List<OrderItem> selectPage(@Param("pager") Pager pager, @Param("where") Map<String, Object> where);

    int update(OrderItem orderItem);

    int deleteById(OrderItem orderItem);
}
