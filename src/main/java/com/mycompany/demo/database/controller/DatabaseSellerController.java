package com.mycompany.demo.database.controller;

import com.mycompany.demo.database.dao.SellerDao;
import com.mycompany.demo.database.entity.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/database/seller")
@RequiredArgsConstructor
public class DatabaseSellerController {

    private final SellerDao sellerDao;

    @GetMapping("/get-seller")
    public Seller getSeller(@RequestParam("sellerId") long sellerId) {
        return sellerDao.selectById(sellerId);
    }

    @PostMapping("/register-seller")
    public Seller registerSeller(@RequestBody Seller seller) {
        sellerDao.insert(seller);
        return sellerDao.selectById(seller.getSellerId());
    }

}
