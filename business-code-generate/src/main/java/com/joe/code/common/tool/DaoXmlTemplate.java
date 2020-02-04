package com.joe.code.common.tool;

public class DaoXmlTemplate extends AbstractCodeTemplate {

    private String mapperName;

    public DaoXmlTemplate(){}
    public DaoXmlTemplate(String mapperName){
        this.mapperName = mapperName;
    }

    @Override
    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        addNewLine(stringBuilder, 0, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        addNewLine(stringBuilder, 0, "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        addNewLine(stringBuilder, 0, "<mapper namespace=\"" + mapperName + "\">");
        forwardNextLine(stringBuilder);
        addNewLine(stringBuilder, 0, "</mapper>");

        return stringBuilder.toString();
    }
}
