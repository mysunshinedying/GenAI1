package com.mycompany.demo.database.service;

import java.util.List;
import java.util.Map;

import com.mycompany.demo.database.dao.OrderItemDao;
import com.mycompany.demo.database.dao.OrdersDao;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.OrderItem;
import com.mycompany.demo.database.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersDao ordersDao;
    private final OrderItemDao orderItemDao;

    // ---------- Orders ----------

    // 주문 조회
    public Orders getOrders(long orderId) {
        return ordersDao.selectById(orderId);
    }

    // 주문 등록
    public int createOrders(Orders orders) {
        return ordersDao.insert(orders);
    }

    // 주문 수정
    public int updateOrders(Orders orders) {
        return ordersDao.update(orders);
    }

    // 주문 삭제
    public int deleteOrders(long orderId) {
        return ordersDao.deleteById(orderId);
    }

    // 주문 목록 조회
    public List<Orders> listOrders(Pager pager, Map<String, Object> where) {
        return ordersDao.selectPage(pager, where);
    }

    // 주문 전체 건수 조회
    public int pagingTotalRows(Map<String, Object> where) {
        return ordersDao.selectCount(where);
    }

    // ---------- OrderItem ----------

    // 주문 상품 조회
    public OrderItem getOrderItem(OrderItem orderItem) {
        return orderItemDao.selectById(orderItem);
    }

    // 주문 상품 등록
    public int createOrderItem(OrderItem orderItem) {
        return orderItemDao.insert(orderItem);
    }

    // 주문 상품 수정
    public int updateOrderItem(OrderItem orderItem) {
        return orderItemDao.update(orderItem);
    }

    // 주문 상품 삭제
    public int deleteOrderItem(OrderItem orderItem) {
        return orderItemDao.deleteById(orderItem);
    }

    // 주문 상품 목록 조회
    public List<OrderItem> listOrderItem(Pager pager, Map<String, Object> where) {
        return orderItemDao.selectPage(pager, where);
    }

    // 주문 상품 전체 건수 조회
    public int pagingOrderItemTotalRows(Map<String, Object> where) {
        return orderItemDao.selectCount(where);
    }
}
