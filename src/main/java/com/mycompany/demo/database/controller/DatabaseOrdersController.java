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

import com.mycompany.demo.database.dao.OrdersDao;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Orders;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/database/orders")
@RequiredArgsConstructor
public class DatabaseOrdersController {
    private final OrdersDao ordersDao;

    @GetMapping("/get-orders")
    public Orders getOrders(@RequestParam("orderId") long orderId) {
        return ordersDao.selectById(orderId);
    }

    @PostMapping("/create-orders")
    public Orders createOrders(@RequestBody Orders orders) {
        ordersDao.insert(orders);
        return ordersDao.selectById(orders.getOrderId());
    }

    @PutMapping("/update-orders")
    public Orders updateOrders(@RequestBody Orders orders) {
        ordersDao.update(orders);
        return ordersDao.selectById(orders.getOrderId());
    }

    @DeleteMapping("/delete-orders")
    public String deleteOrders(@RequestParam("orderId") long orderId) {
        int rows = ordersDao.deleteById(orderId);
        return "orderId: " + orderId + (rows > 0 ? " deleted" : " not found");
    }

    @GetMapping("/list-orders")
    public Map<String, Object> listOrders(
            @RequestParam("pageNo") int pageNo,
            @RequestParam(value = "orderId", required = false) Long orderId,
            @RequestParam(value = "memberId", required = false) String memberId) {
        Map<String, Object> where = new HashMap<>();
        if (orderId != null) where.put("orderId", orderId);
        if (memberId != null && !memberId.isBlank()) where.put("memberId", memberId);

        int totalRows = ordersDao.selectCount(where);
        Pager pager = new Pager(10, 10, totalRows, pageNo);
        List<Orders> list = ordersDao.selectPage(pager, where);
        return Map.of("pager", pager, "data", list);
    }
}

