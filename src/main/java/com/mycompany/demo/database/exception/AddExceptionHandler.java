package com.mycompany.demo.database.exception;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Slf4j
public class AddExceptionHandler {

    @ExceptionHandler(DuplicatedMemberIDException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicatedMemberIDException(DuplicatedMemberIDException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "fail");
        map.put("message", e.getMessage());
        map.put("methodName", "handleDuplicatedMemberIDException");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(map);
    }

    @ExceptionHandler(NoAccountException.class)
    public ResponseEntity<Map<String, Object>> handleNoAccountException(NoAccountException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "fail");
        map.put("message", e.getMessage());
        map.put("methodName", "handleNoAccountException");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(map);
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoResourceFoundException(NoResourceFoundException e) {
        log.info(e.getClass().toString());
        Map<String, Object> map = new HashMap<>();
        map.put("status", "fail");
        map.put("message", "장난ㄴㄴ");
        map.put("methodName", "handleNoResourceFoundException");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<Map<String, Object>> handleSQLException(PSQLException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "fail");
        map.put("message", e.getMessage());
        map.put("methodName", "handleSQLException");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(map);
    }

    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "fail");
        map.put("message", e.getMessage());
        map.put("methodName", "handleException");
        return map;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, Object>> handleNullPointerException(NullPointerException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "fail");
        map.put("message", e.getMessage());
        map.put("methodName", "handleNullPointerException");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(map);
    }


}
