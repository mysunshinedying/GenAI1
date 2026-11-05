package com.mycompany.demo.database.exception;

public class NoAccountException extends RuntimeException {

    public NoAccountException(String message) {
        super(message);
    }
}
