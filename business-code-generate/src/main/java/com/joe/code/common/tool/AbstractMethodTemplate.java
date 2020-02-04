package com.joe.code.common.tool;

import com.joe.code.common.enums.BasicControlTypeEnum;

public abstract class AbstractMethodTemplate extends AbstractCodeTemplate {

    protected final String EMPTY_POSITION_FOUR = "    ";

    private String entityName;
    private String tableRemark;
    private BasicControlTypeEnum basicControlTypeEnum;

    AbstractMethodTemplate(){}
    AbstractMethodTemplate(String entityName, String tableRemark, BasicControlTypeEnum basicControlTypeEnum){

        this.entityName = entityName;
        this.tableRemark = tableRemark;
        this.basicControlTypeEnum = basicControlTypeEnum;
    }

    protected abstract String getMethodHeader(BasicControlTypeEnum basicControlTypeEnum, String entityName);

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getTableRemark() {
        return tableRemark;
    }

    public void setTableRemark(String tableRemark) {
        this.tableRemark = tableRemark;
    }

    public BasicControlTypeEnum getBasicControlTypeEnum() {
        return basicControlTypeEnum;
    }

    public void setBasicControlTypeEnum(BasicControlTypeEnum basicControlTypeEnum) {
        this.basicControlTypeEnum = basicControlTypeEnum;
    }
}
