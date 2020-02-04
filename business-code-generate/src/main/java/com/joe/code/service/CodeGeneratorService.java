package com.joe.code.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joe.code.common.enums.BasicControlTypeEnum;
import com.joe.code.common.tool.*;
import com.joe.code.common.utils.*;
import com.joe.code.entity.SysTableField;
import com.joe.code.entity.SysTableInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class CodeGeneratorService {

    @Autowired
    private SysTableFieldService sysTableFieldService;

    public String getEntityContent(SysTableInfo sysTableInfo, String packageName){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package ").append(packageName).append(";\r\n").append("\n");
//        stringBuilder.append("import java.util.Date;").append("\r\n");
        stringBuilder.append("import lombok.Data;").append("\r\n").append("\n");
        stringBuilder.append("@Data").append("\r\n");
        stringBuilder.append("public class ").append(EntityCodeUtil.getEntityNameByTable(sysTableInfo.getTableName())).append(" {\r\n\n");
        for(SysTableField sysTableField : selectListByTable(sysTableInfo.getId())){
            stringBuilder.append(EntityCodeUtil.getFieldDeclareContent(sysTableField.getDataType(), sysTableField.getTableFieldName(), sysTableField.getTableFieldRemark()));
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public String getControlContent(SysTableInfo sysTableInfo, String packageName){

        CodeBack codeBack = new ControlFileTemplate(sysTableInfo, packageName);
        return codeBack.getContent();
    }

    public String getServiceContent(SysTableInfo sysTableInfo, String packageName){
        CodeBack codeBack = new ServiceFileTemplate(sysTableInfo.getTableName(), packageName);
        return codeBack.getContent();
    }

    public String getDaoContent(SysTableInfo sysTableInfo, String packageName){

        String entityName = EntityCodeUtil.getEntityNameByTable(sysTableInfo.getTableName());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package ").append(packageName).append(";\r\n").append("\n");
//        stringBuilder.append("import java.util.Date;").append("\r\n");
//        stringBuilder.append("import lombok.Data;").append("\r\n").append("\n");
        stringBuilder.append("@Repository").append("\r\n");
        stringBuilder.append("public interface ").append(entityName).append("Mapper extends BaseMapper<").append(entityName).append("> {\r\n\n");

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public String getDaoXmlContent(SysTableInfo sysTableInfo, String packageName){

        String entityName = EntityCodeUtil.getEntityNameByTable(sysTableInfo.getTableName());

        CodeBack codeBack = new DaoXmlTemplate(packageName + "."+ entityName + "Mapper");
        return codeBack.getContent();
    }

    /**
     * 根据表查询所有字段
     * @param tableId
     * @return
     */
    private List<SysTableField> selectListByTable(Integer tableId){
        QueryWrapper<SysTableField> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sys_table_id", tableId);
        return sysTableFieldService.list(queryWrapper);
    }
}
