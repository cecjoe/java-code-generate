package com.joe.code.common.utils;

import com.joe.code.common.annotations.NotQueryField;
import lombok.Data;

@Data
public class TableFieldInfo {

    private String isNull;

    @NotQueryField
    private Integer isNullable;

    private String dataTypeSign;

    @NotQueryField
    private Integer dataType;

    private String tableFieldName;

    private String tableFieldRemark;

    public Integer getIsNullable(){

        if("YES".equals(isNull)){
            return 1;
        }else{
            return 0;
        }
    }

    public Integer getDataType(){

        if("varchar".equals(dataTypeSign)){
            return 0;
        }else if("tinyint".equals(dataTypeSign) || "int".equals(dataTypeSign)){
            return 1;
        }else if("bigint".equals(dataTypeSign)){
            return 2;
        }else if("timestamp".equals(dataTypeSign) || "datetime".equals(dataTypeSign)){
            return 3;
        }else{}

        return null;
    }
}
