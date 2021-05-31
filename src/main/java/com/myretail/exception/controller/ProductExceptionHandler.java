package com.myretail.exception.controller;

import com.myretail.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler({
            ProductException.class
    })
    public ResponseEntity<ErrorMsg> handleProductException(ProductException e) {
        return new ResponseEntity<>(new ErrorMsg(e.getMsg()), e.getStatus());
    }

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ErrorMsg> handleException(Exception e) {
        return new ResponseEntity(new ErrorMsg(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
