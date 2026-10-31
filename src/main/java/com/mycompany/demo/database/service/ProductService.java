package com.mycompany.demo.database.service;

import java.util.List;
import java.util.Map;

import com.mycompany.demo.database.dao.BoardDao;
import com.mycompany.demo.database.dao.ProductDao;
import com.mycompany.demo.database.dao.SellerDao;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Board;
import com.mycompany.demo.database.entity.Product;
import com.mycompany.demo.database.entity.Seller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final SellerDao sellerDao;
    private final BoardDao boardDao;

    // ---------- Seller ----------

    // 판매자 조회
    public Seller getSeller(long sellerId) {
        return sellerDao.selectById(sellerId);
    }

    // 판매자 등록
    public int registerSeller(Seller seller) {
        return sellerDao.insert(seller);
    }

    // 판매자 수정
    public int updateSeller(Seller seller) {
        return sellerDao.update(seller);
    }

    // 판매자 삭제
    public int deleteSeller(long sellerId) {
        return sellerDao.deleteById(sellerId);
    }

    // 판매자 목록 조회
    public List<Seller> listSeller(Pager pager, Map<String, Object> where) {
        return sellerDao.selectPage(pager, where);
    }

    // 판매자 전체 건수 조회
    public int pagingTotalRows(Map<String, Object> where) {
        return sellerDao.selectCount(where);
    }

    // ---------- Board ----------

    // 게시글 조회
    public Board getBoard(long boardId) {
        return boardDao.selectById(boardId);
    }

    // 게시글 등록
    public int writeBoard(Board board) {
        return boardDao.insert(board);
    }

    // 게시글 수정
    public int updateBoard(Board board) {
        return boardDao.update(board);
    }

    // 게시글 삭제
    public int deleteBoard(String boardId) {
        return boardDao.deleteById(boardId);
    }

    // ---------- Product ----------

    // 상품 조회
    public Product getProduct(String productId) {
        return productDao.selectById(productId);
    }

    // 상품 등록
    public int createProduct(Product product) {
        return productDao.insert(product);
    }

    // 상품 수정
    public int updateProduct(Product product) {
        return productDao.update(product);
    }

    // 상품 삭제
    public int deleteProduct(String productId) {
        return productDao.deleteById(productId);
    }
}



