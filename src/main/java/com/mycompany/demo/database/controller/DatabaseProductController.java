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

import com.mycompany.demo.database.dao.ProductDao;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Product;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/database/product")
@RequiredArgsConstructor
public class DatabaseProductController {
    private final ProductDao productDao;

    @GetMapping("/get-product")
    public Product getProduct(@RequestParam("productId") long productId) {
        return productDao.selectById(productId);
    }

    @PostMapping("/create-product")
    public Product createProduct(@RequestBody Product product) {
        productDao.insert(product);
        return productDao.selectById(product.getProductId());
    }

    @PutMapping("/update-product")
    public Product updateProduct(@RequestBody Product product) {
        productDao.update(product);
        return productDao.selectById(product.getProductId());
    }

    @DeleteMapping("/delete-product")
    public String deleteProduct(@RequestParam("productId") long productId) {
        int rows = productDao.deleteById(productId);
        return "productId: " + productId + (rows > 0 ? " deleted" : " not found");
    }

    @GetMapping("/list-product")
    public Map<String, Object> listProduct(
            @RequestParam("pageNo") int pageNo,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "sellerId", required = false) Long sellerId) {
        Map<String, Object> where = new HashMap<>();
        if (productName != null && !productName.isBlank()) where.put("productName", productName);
        if (sellerId != null) where.put("sellerId", sellerId);

        int totalRows = productDao.selectCount(where);
        Pager pager = new Pager(10, 10, totalRows, pageNo);
        List<Product> list = productDao.selectPage(pager, where);
        return Map.of("pager", pager, "data", list);
    }
}

