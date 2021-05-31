package com.myretail.exception.controller;

import java.io.Serializable;

public class ErrorMsg implements Serializable {
    private String msg;

    public ErrorMsg(String msg) {
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
