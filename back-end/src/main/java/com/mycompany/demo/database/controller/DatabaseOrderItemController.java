package com.mycompany.demo.database.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.database.dao.OrderItemDao;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.OrderItem;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/database/order-item")
@RequiredArgsConstructor
public class DatabaseOrderItemController {
    private final OrderItemDao orderItemDao;

    @GetMapping("/get-order-item")
    public OrderItem getOrderItem(
            @RequestParam("orderId") long orderId,
            @RequestParam("productId") long productId) {
        OrderItem key = new OrderItem(orderId, productId, 0, 0);
        return orderItemDao.selectById(key);
    }

    @PostMapping("/create-order-item")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        orderItemDao.insert(orderItem);
        return orderItemDao.selectById(orderItem);
    }

    @PutMapping("/update-order-item")
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem) {
        orderItemDao.update(orderItem);
        return orderItemDao.selectById(orderItem);
    }

    @DeleteMapping("/delete-order-item")
    public String deleteOrderItem(
            @RequestParam("orderId") long orderId,
            @RequestParam("productId") long productId) {
        OrderItem key = new OrderItem(orderId, productId, 0, 0);
        int rows = orderItemDao.deleteById(key);
        return "orderId: " + orderId + ", productId: " + productId
                + (rows > 0 ? " deleted" : " not found");
    }

    @GetMapping("/list-order-item")
    public Map<String, Object> listOrderItem(
            @RequestParam("pageNo") int pageNo,
            @RequestParam(value = "orderId", required = false) Long orderId,
            @RequestParam(value = "productId", required = false) Long productId) {
        Map<String, Object> where = new HashMap<>();
        if (orderId != null) where.put("orderId", orderId);
        if (productId != null) where.put("productId", productId);

        int totalRows = orderItemDao.selectCount(where);
        Pager pager = new Pager(10, 10, totalRows, pageNo);
        List<OrderItem> list = orderItemDao.selectPage(pager, where);
        return Map.of("pager", pager, "data", list);
    }
}

