package com.joe.code.common.utils;

import lombok.Data;

@Data
public class ResultObject<T> {

    private int code;
    private String msg;
    private T data;
}
