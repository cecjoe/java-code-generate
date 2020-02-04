package com.joe.code.entity;

import lombok.Data;

@Data
public class SysProject {

    /**
    * 主键ID
    */
    private Integer id;

    /**
    * 系统名称
    */
    private String sysName;

    /**
    * 数据库连接URL
    */
    private String dbUrl;

    /**
    * 数据库名
    */
    private String dbName;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 密码
    */
    private String userPass;

    /**
    * 项目的根目录
    */
    private String rootPath;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 控制层文件夹名
    */
    private String controlName;

    /**
    * 业务层文件夹名
    */
    private String serviceName;

    /**
    * 持久层文件夹名
    */
    private String daoName;

    /**
    * 状态；0-创建完成；1-预处理完成；2-关系划分完成；3-生成代码
    */
    private Integer state;

}