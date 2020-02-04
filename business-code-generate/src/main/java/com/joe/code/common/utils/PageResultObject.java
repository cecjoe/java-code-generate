package com.joe.code.common.utils;

import lombok.Data;

@Data
public class PageResultObject<M> extends ResultObject {

    private long count;
}
