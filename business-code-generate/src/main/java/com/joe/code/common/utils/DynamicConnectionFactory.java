package com.joe.code.common.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Data
public class DynamicConnectionFactory {

    private String url = null;
    private String user = null;
    private String password = null;

    public static DynamicConnection createDynamicConnection(String url, String user, String password) {
        try {
            DynamicConnection dynamicConnection = new DynamicConnection();
            Class.forName("com.mysql.jdbc.Driver");
            dynamicConnection.setConnection(DriverManager.getConnection(url, user, password));
            return dynamicConnection;
        }catch (ClassNotFoundException e){
            log.error("类找不到异常：" + e.getMessage());
            throw new IllegalArgumentException("服务器异常！");
        }catch (SQLException e){
            log.error("SQL异常：" + e.getMessage());
            throw new IllegalArgumentException("服务器异常！");
        }
    }
}
