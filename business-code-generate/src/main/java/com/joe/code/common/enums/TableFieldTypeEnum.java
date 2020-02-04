package com.joe.code.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum TableFieldTypeEnum {

    FIELD_STRING(0, "String"), FIELD_INT(1, "Integer"), FIELD_LONG(2, "Long"), FIELD_DATE(3, "Date");

//    字段类型；0-字符串；1-整型；2-长整型；3-日期类型;4-浮点型；5-双精度浮点型；

    private Integer index;
    private String label;

    public static Map<Integer, String> mapData;

    static{
        mapData = new HashMap<>();
        for(TableFieldTypeEnum tableFieldTypeEnum : TableFieldTypeEnum.values()){
            mapData.put(tableFieldTypeEnum.getIndex(), tableFieldTypeEnum.getLabel());
        }
    }
}
