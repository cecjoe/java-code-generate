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
     * 数据库URL地址
     */
    private String dbUrl;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 数据库连接用户名
     */
    private String userName;

    /**
     * 数据库连接密码
     */
    private String userPass;

    /**
     * 项目主包名
     */
    private String rootPath;

    /**
     * 控制器文件夹名称
     */
    private String controlName;

    /**
     * 业务层文件夹名称
     */
    private String serviceName;

    /**
     * 持久层文件夹名称
     */
    private String daoName;

    /**
     * 状态；0-创建完成；1-预处理完成；2-关系划分完成；3-生成代码
     */
    private Integer state;

    /**
     * 编译次数
     */
    private Integer generateTimes;

}
