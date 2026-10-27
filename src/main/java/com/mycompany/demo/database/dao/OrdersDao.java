package com.mycompany.demo.database.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Orders;

@Mapper
public interface OrdersDao {
    int insert(Orders orders);

    Orders selectById(long orderId);

    int selectCount(@Param("where") Map<String, Object> where);

    List<Orders> selectPage(@Param("pager") Pager pager, @Param("where") Map<String, Object> where);

    int update(Orders orders);

    int deleteById(long orderId);
}
