package com.mycompany.demo.database.dao;

import java.util.List;
import java.util.Map;
import com.mycompany.demo.database.dto.Pager;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.demo.database.entity.Seller;

@Mapper
public interface SellerDao {
    int insert(Seller seller);

    Seller selectById(long sellerId);

    int selectCount(@Param("where") Map<String, Object> where);

    List<Seller> selectPage(@Param("pager") Pager pager, @Param("where") Map<String, Object> where);

    int update(Seller seller);

    int deleteById(long sellerId);
}