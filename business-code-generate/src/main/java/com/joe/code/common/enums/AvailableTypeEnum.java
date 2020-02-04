package com.joe.code.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AvailableTypeEnum {

    YES(1, "是"), No(0, "否");

    private Integer index;
    private String label;
}
