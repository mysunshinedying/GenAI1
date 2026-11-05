package com.mycompany.demo.receivedata.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.demo.receivedata.dto.Product;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController 
@RequestMapping ("/receivedata")
@Slf4j 
public class ReceiveDataController {
    // RequestParam 통한 Data 수신
    @GetMapping (value = "/request-param", params = "pno")
    public String getRequestParam(@RequestParam("pno") String pno) {
        log.info("Received pno: {}", pno);
        return "실행 결과";
    }

    // RequestHeader 수신
    @GetMapping (value = "/request-header", headers = "{Authorization}")
    public String getRequestHeader(@RequestHeader("Authorization") String authorization) {
        log.info("Received Authorization header: {}", authorization);
        return "실행 결과";
    }

    // DTO를 RequestParam 통하여 수신
    @PostMapping(value = "/request-param-dto")
    public Product getRequestParamDto(@RequestParam ("pno") int pno,
                                      @RequestParam("name") String name,
                                      @RequestParam("rating") double rating,
                                      @RequestParam("price") double price,
                                      @RequestParam("stock") int stock,
                                      @RequestParam("description") String description) {
        Product product = new Product(pno, name, rating, price, stock, description, null);
        log.info("Received Product DTO via RequestParam: {}", product);
        return product;
    }

    // RequestBody를 통한 DTO 수신
    @PostMapping(value = "/request-body-dto")
    public Product getRequestBodyDto(@RequestBody Product product) {
        log.info("Received Product Body via RequestBody: {}", product);
        return product;
    }

    // PathVariable 통한 Data 수신
    @GetMapping(value = "/path-variable/{pno}")
    public String getPathVariable(@org.springframework.web.bind.annotation.PathVariable("pno") String pno) {
        log.info("Received Product Number via PathVariable: {}", pno);
        return "실행 결과";
    }

    // RequestPart 통한 Data 수신(form encoding or formData)
    // RequestPart의 이점: multipart/form-data 요청에서 파일과 데이터를 동시에 수신할 수 있다
    @PostMapping(value = "/request-part-dto",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Product getRequestPartDto(@RequestPart("pno") int pno,
                                     @RequestPart("name") String name,
                                     @RequestPart("rating") double rating,
                                     @RequestPart("price") double price,
                                     @RequestPart("stock") int stock,
                                     @RequestPart("description") String description,
                                     @RequestPart("image") MultipartFile image) {
        Product product = new Product(pno, name, rating, price, stock, description, image);
        log.info("Received Product DTO via RequestPart: {}", product);
        return product;
    }

    // CookieValue를 위한 Cookie Set
    @GetMapping(value = "/set-cookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "Chocolate Cookie");
        response.addCookie(cookie);
        return "Cookie has been set";
    }

    @GetMapping(value = "/get-cookie")
    public String getCookie(@CookieValue(value = "myCookie", defaultValue = "defaultCookieValue") String myCookie) {
        log.info("Received Cookie: {}", myCookie);
        return "My Cookie is...  " + myCookie;
    }

}
