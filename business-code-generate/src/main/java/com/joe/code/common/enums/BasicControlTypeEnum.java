package com.joe.code.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
public enum BasicControlTypeEnum {

    SELECT_BY_PAGE("selectByPage", "分页查询", 0, "@GetMapping(\"/list\")", 0),
    ADD_NEW("add", "新增", 1, "@PostMapping(\"/create\")", 0),
    UPDATE_OLD("update", "修改", 1, "@PutMapping(\"/update\")", 0),
    DELETE_OLD("delete", "删除", 1, "@DeleteMapping(\"/delete/{id}\")", 1);

    private String code;
    private String label;
    private Integer change;
    private String mapping;
    private Integer isPath;

//    public String getMapping(){
//
//
//    }
}
