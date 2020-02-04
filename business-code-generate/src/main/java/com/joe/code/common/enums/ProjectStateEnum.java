package com.joe.code.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目状态枚举
 */
@AllArgsConstructor
@Getter
public enum ProjectStateEnum {

    CREATED(0, "创建完成"), PREPROCESSED(1, "预处理完成"), TABLE_RELATED(2, "表关联完成"), CODE_GENERATED(3, "代码生成");

    private Integer index;
    private String label;

    public static Map<Integer, String> mapData;

    static{
        mapData = new HashMap<>();
        for(ProjectStateEnum projectStateEnum : ProjectStateEnum.values()){
            mapData.put(projectStateEnum.getIndex(), projectStateEnum.getLabel());
        }
    }
}
