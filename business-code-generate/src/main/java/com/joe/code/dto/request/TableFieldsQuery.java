package com.joe.code.dto.request;

import lombok.Data;

@Data
public class TableFieldsQuery {
    private String dbName;
    private String tableName;
}
