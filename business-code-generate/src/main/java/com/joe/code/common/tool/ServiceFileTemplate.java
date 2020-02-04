package com.joe.code.common.tool;

import com.joe.code.common.utils.EntityCodeUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServiceFileTemplate extends AbstractCodeTemplate  {

    private String tableName;
    private String packageName;



    @Override
    public String getContent() {
        String entityName = EntityCodeUtil.getEntityNameByTable(tableName);
        StringBuilder stringBuilder = new StringBuilder();
        addNewLine(stringBuilder, 0, "package " + packageName + ";");
        forwardNextLine(stringBuilder);

        addNewLine(stringBuilder, 0, "@Service");
        addNewLine(stringBuilder, 0, getServiceNameHead(entityName));
        forwardNextLine(stringBuilder);

        addNewLine(stringBuilder, 0, "}");
        return stringBuilder.toString();
    }

    private String getServiceNameHead(String entityName){
        StringBuilder sb = new StringBuilder();
        sb.append("public class ").append(entityName).append("Service ")
                .append("extends ServiceImpl<").append(entityName).append("Mapper, ")
                .append(entityName).append(">{");
        return sb.toString();
    }
}
