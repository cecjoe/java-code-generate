package com.joe.code.entity;

import lombok.Data;
import java.util.Date;

@Data
public class SysTableInfo {

    /**
     * ID主键
     */
    private Integer id;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableRemark;

    /**
     * 项目ID
     */
    private Integer sysProjectId;

    /**
     * 创建时间
     */
    private Date createTime;
}
