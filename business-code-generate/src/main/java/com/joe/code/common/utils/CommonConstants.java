package com.joe.code.common.utils;

public class CommonConstants {

    public static final String PROJECT_DEFAULT_CONTROL_NAME = "controller";
    public static final String PROJECT_DEFAULT_SERVICE_NAME = "service";
    public static final String PROJECT_DEFAULT_DAO_NAME = "mapper";

    /**
     * 常用SQL 定义
     */
    //查询数据库中的所有表
    public static final String QUERY_ALL_TABLES_BY_DB = "select table_comment table_remark,table_name from information_schema.tables " +
            "where table_schema={dbName} and table_type='BASE TABLE';";

    /**
     * 查询数据库表中所有的字段
     */
    public static final String QUERY_ALL_FIELDS_BY_TB = "select is_nullable is_null,data_type data_type_sign,column_name table_field_name,column_comment table_field_remark from information_schema.columns " +
            "where table_schema={dbName} and table_name={tableName} ORDER BY ordinal_position ";

}
