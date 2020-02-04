package com.joe.code.common.utils;

import com.joe.code.common.enums.TableFieldTypeEnum;
import org.springframework.util.StringUtils;

public class EntityCodeUtil {

    private static final String EMPTY_POSITION_FOUR = "    ";

    /**
     * 根据表名获取实体名称
     * @param tableName
     * @return
     */
    public static String getEntityNameByTable(String tableName){
        if(StringUtils.isEmpty(tableName)){
            return null;
        }
        String basicEntityName = ChangeChar.underlineToCamel(tableName);
        return basicEntityName.substring(0, 1).toUpperCase()+basicEntityName.substring(1);
    }

    public static String getImportPageByDataTye(){
        return null;
    }

    public static String getFieldDeclareContent(Integer dataType, String tableFieldName, String tableFieldRemark){

        StringBuilder stringBuilder = new StringBuilder();
        if(!StringUtils.isEmpty(tableFieldRemark)){

            addNewLine(stringBuilder, 1, "/**");
            addNewLine(stringBuilder, 1, "* " + tableFieldRemark);
            addNewLine(stringBuilder, 1, "*/");
        }

        addNewLine(stringBuilder, 1, getField(dataType, tableFieldName));
        forwardNextLine(stringBuilder);
        return stringBuilder.toString();
    }

    private static String getField(Integer dataType,String tableFieldName){
        return "private " + TableFieldTypeEnum.mapData.get(dataType) + " " + ChangeChar.underlineToCamel(tableFieldName) + ";";
    }

    protected static void addNewLine(StringBuilder stringBuilder,Integer depth, String appendContent){

        for(int i = 0; i < depth; i++){
            stringBuilder.append(EMPTY_POSITION_FOUR);
        }
        stringBuilder.append(appendContent);
        forwardNextLine(stringBuilder);
    }

    protected static void forwardNextLine(StringBuilder stringBuilder){

        stringBuilder.append("\r\n");
    }
}
