package com.joe.code.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChangeTypeEnum {

    ENABLE_CHANGE(1, "可以改变"), DISABLED_CHANGE(0, "不可改变");

    private Integer index;
    private String remark;
}
