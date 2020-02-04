package com.joe.code.entity;

import lombok.Data;

@Data
public class SysTableField {

    /**
    * ID主键
    */
    private Integer id;

    /**
    * 表ID
    */
    private Integer sysTableId;

    /**
    * 字段名
    */
    private String tableFieldName;

    /**
    * 字段类型；0-字符串；1-整型；2-长整型；3-日期类型;4-浮点型；5-双精度浮点型；
    */
    private Integer dataType;

    /**
    * 字段注释
    */
    private String tableFieldRemark;

    /**
    * 是否可为空;1-可为空；0-不能为空
    */
    private Integer isNullable;

}