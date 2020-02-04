package com.joe.code.common.utils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;

@Slf4j
@Setter
public class DynamicConnection {

    private AbstractDynamicExecutor dynamicExecutor = null;

    private Connection connection = null;

    public Connection getConnection(){
        if(connection == null) {
            throw new IllegalArgumentException("数据库连接找不到！");
        }
        return connection;
    }

    public void releaseConnection() {
        dynamicExecutor.closeConnection();
    }
}
