package com.joe.code.common.tool;

import com.joe.code.common.enums.BasicControlTypeEnum;
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

        addNewLine(stringBuilder, 0, "import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;");
        addNewLine(stringBuilder, 0, "import org.springframework.stereotype.Service;");
        addNewLine(stringBuilder, 0, "import " + packageName.replaceAll(".service", "") + ".entity." + entityName + ";");
        addNewLine(stringBuilder, 0, "import " + packageName.replaceAll(".service", "") + ".mapper." + entityName + "Mapper;");
        addNewLine(stringBuilder, 0, "import lombok.extern.slf4j.Slf4j;");
        forwardNextLine(stringBuilder);

        addNewLine(stringBuilder, 0, "@Service");
        addNewLine(stringBuilder, 0, "@Slf4j");
        addNewLine(stringBuilder, 0, getServiceNameHead(entityName));
        forwardNextLine(stringBuilder);

        for(BasicControlTypeEnum basicControlTypeEnum : BasicControlTypeEnum.values()){

            if(basicControlTypeEnum.getCode().equals(BasicControlTypeEnum.SELECT_BY_PAGE.getCode())){
                continue;
            }

            CodeBack codeBack = new ServiceMethodTemplate(entityName, null, basicControlTypeEnum);

            addNewLine(stringBuilder, 1, codeBack.getContent());
        }

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
