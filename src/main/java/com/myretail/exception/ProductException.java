package com.myretail.exception;

import org.springframework.http.HttpStatus;

public class ProductException extends RuntimeException {
    private HttpStatus status;
    private String msg;

    public ProductException(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
