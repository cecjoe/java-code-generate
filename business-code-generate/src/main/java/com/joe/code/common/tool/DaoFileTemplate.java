package com.joe.code.common.tool;

import com.joe.code.common.utils.EntityCodeUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DaoFileTemplate extends AbstractCodeTemplate {

    private String tableName;
    private String packageName;

    @Override
    public String getContent() {
        String entityName = EntityCodeUtil.getEntityNameByTable(tableName);

        StringBuilder stringBuilder = new StringBuilder();
        addNewLine(stringBuilder, 0, "package " + packageName + ";");
        forwardNextLine(stringBuilder);
        addNewLine(stringBuilder, 0, "@Repository");
        addNewLine(stringBuilder, 0, getDaoNameHead(entityName));
        forwardNextLine(stringBuilder);

        CodeBack codeBack = new DaoMethoddTemplate(entityName);
        addNewLine(stringBuilder, 1, codeBack.getContent());

//        stringBuilder.append("public interface ").append(entityName).append("Mapper extends BaseMapper<").append(entityName).append("> {\r\n\n");
        addNewLine(stringBuilder, 0, "}");
        return stringBuilder.toString();
    }

    private String getDaoNameHead(String entityName){
        return "public interface " + entityName + "Mapper extends BaseMapper<" + entityName + "> {";
    }
}
