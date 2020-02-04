package com.joe.code.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddProjectReq {

    /**
     * 系统名称
     */
    @NotEmpty(message = "项目名称不能为空！")
    private String sysName;

    /**
     * 数据库URL地址
     */
    @NotEmpty(message = "库链接不能为空！")
    private String dbUrl;

    /**
     * 请输入库名称
     */
    @NotEmpty(message = "请输入库名称")
    private String dbName;

    /**
     * 数据库连接用户名
     */
    @NotEmpty(message = "用户名不能为空！")
    private String userName;

    /**
     * 数据库连接密码
     */
    @NotEmpty(message = "密码不能为空！")
    private String userPass;

    /**
     * 项目主包名
     */
    @NotEmpty(message = "包路径不能为空！")
    private String rootPath;
}
