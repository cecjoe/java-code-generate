package com.joe.code.common.tool;

import com.joe.code.common.enums.BasicControlTypeEnum;

public class ServiceMethodTemplate extends AbstractMethodTemplate {

    public ServiceMethodTemplate(){}
    public ServiceMethodTemplate(String entityName, String tableRemark, BasicControlTypeEnum basicControlTypeEnum){

        super(entityName, tableRemark, basicControlTypeEnum);
    }

    @Override
    public String getContent() {

        StringBuilder stringBuilder = new StringBuilder();
        addNewLine(stringBuilder, 0, getMethodHeader());
        forwardNextLine(stringBuilder);
        addNewLine(stringBuilder, 1, "}");
        return stringBuilder.toString();
    }

    @Override
    protected String getMethodHeader() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("public void ").append(getBasicControlTypeEnum().getCode())
                .append(getEntityName()).append("(" + getMethodParamDifine() + "){");
        return stringBuilder.toString();
    }

    private String getMethodParamDifine(){

        if(getBasicControlTypeEnum().getCode().equals(BasicControlTypeEnum.DELETE_OLD.getCode())){
            return "Integer id";
        }else{
            return getFirstCharIsUpper(getBasicControlTypeEnum().getCode()) + getEntityName().replaceAll("Sys", "") + "Req req";
        }
    }
}
