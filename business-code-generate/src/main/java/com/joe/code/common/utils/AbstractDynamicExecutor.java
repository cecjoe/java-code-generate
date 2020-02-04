package com.joe.code.common.utils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractDynamicExecutor {

    private DynamicConnection dynamicConnection = null;

    AbstractDynamicExecutor(){}
    AbstractDynamicExecutor(DynamicConnection dynamicConnection){
        this.dynamicConnection = dynamicConnection;
    }

    protected abstract void closeConnection();

}
