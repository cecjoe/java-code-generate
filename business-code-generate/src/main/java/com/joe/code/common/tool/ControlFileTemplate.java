package com.joe.code.common.tool;

import com.joe.code.common.enums.BasicControlTypeEnum;
import com.joe.code.common.utils.ChangeChar;
import com.joe.code.common.utils.EntityCodeUtil;
import com.joe.code.entity.SysTableInfo;

public class ControlFileTemplate extends AbstractCodeTemplate {

    private SysTableInfo sysTableInfo;
    private String packageName;

    public ControlFileTemplate(){}
    public ControlFileTemplate(SysTableInfo sysTableInfo, String packageName){
        this.sysTableInfo = sysTableInfo;
        this.packageName = packageName;
    }

    @Override
    public String getContent() {
        String entityName = EntityCodeUtil.getEntityNameByTable(sysTableInfo.getTableName());

        StringBuilder stringBuilder = new StringBuilder();
        addNewLine(stringBuilder, 0, "package " + packageName + ";");
        forwardNextLine(stringBuilder);

        addNewLine(stringBuilder, 0, "import com.baomidou.mybatisplus.extension.plugins.pagination.Page;");
        addNewLine(stringBuilder, 0, "import org.springframework.web.bind.annotation.*;");
        addNewLine(stringBuilder, 0, "import javax.validation.Valid;");
        addNewLine(stringBuilder, 0, "import org.springframework.beans.factory.annotation.Autowired;");

        addNewLine(stringBuilder, 0, "import com.joe.code.common.enums.ResultStatusEnum;");
        addNewLine(stringBuilder, 0, "import com.joe.code.common.utils.ResultObject;");
        addNewLine(stringBuilder, 0, "import " + packageName.replaceAll(".controller", "") + ".service." + entityName + "Service;");
        forwardNextLine(stringBuilder);


        addNewLine(stringBuilder, 0, "@RestController");
        addNewLine(stringBuilder, 0, "@RequestMapping(\"/" + ChangeChar.underlineToCamel(sysTableInfo.getTableName()) + "\")");
        addNewLine(stringBuilder, 0, "public class " + entityName + "Controller {");
        forwardNextLine(stringBuilder);

        addNewLine(stringBuilder, 1, "@Autowired");
        addNewLine(stringBuilder, 1, "private " + entityName + "Service " + ChangeChar.underlineToCamel(sysTableInfo.getTableName()) + "Service;");
        forwardNextLine(stringBuilder);

        for(BasicControlTypeEnum basicControlTypeEnum : BasicControlTypeEnum.values()){
            CodeBack codeBack = new ControlMethodTemplate(entityName, sysTableInfo.getTableRemark(), basicControlTypeEnum);
            stringBuilder.append(codeBack.getContent());
        }
        addNewLine(stringBuilder, 0, "}");
        return stringBuilder.toString();
    }
}
