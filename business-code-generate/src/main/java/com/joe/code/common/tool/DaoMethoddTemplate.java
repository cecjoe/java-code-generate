package com.joe.code.common.tool;

import com.joe.code.common.enums.BasicControlTypeEnum;

public class DaoMethoddTemplate extends AbstractMethodTemplate {

    public DaoMethoddTemplate(){}
    public DaoMethoddTemplate(String entityName){

        super(entityName, null, null);
    }

    @Override
    protected String getMethodHeader() {
        String resView = getEntityName() + "View";
        return "List<" + resView + "> selectByPage(Page<" + resView + "> page);";
    }

    @Override
    public String getContent() {
        return getMethodHeader();
    }
}
