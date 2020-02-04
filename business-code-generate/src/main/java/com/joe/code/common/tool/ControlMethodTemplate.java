package com.joe.code.common.tool;

import com.joe.code.common.enums.AvailableTypeEnum;
import com.joe.code.common.enums.BasicControlTypeEnum;
import com.joe.code.common.enums.ChangeTypeEnum;
import org.springframework.util.StringUtils;

public class ControlMethodTemplate extends AbstractMethodTemplate {

    private static final String PAGE_LABEL = "page";

    public ControlMethodTemplate(){}
    public ControlMethodTemplate(String entityName, String tableRemark, BasicControlTypeEnum basicControlTypeEnum){

        super(entityName, tableRemark, basicControlTypeEnum);
    }

    @Override
    public String getContent(){

        String methodRemark = getBasicControlTypeEnum().getLabel() + getTableRemark();
        StringBuilder stringBuilder = new StringBuilder();
        if(!StringUtils.isEmpty(methodRemark)){
            addNewLine(stringBuilder, 1, "/**");
            addNewLine(stringBuilder, 1, "* "+methodRemark);
            addNewLine(stringBuilder, 1, "*/");
        }

        addNewLine(stringBuilder, 1, getBasicControlTypeEnum().getMapping());
        addNewLine(stringBuilder, 1, getMethodHeader(getBasicControlTypeEnum(), getEntityName()));
        stringBuilder.append(getCallServiceFunc(getBasicControlTypeEnum(), getEntityName()));
        addNewLine(stringBuilder, 1, "}");
        forwardNextLine(stringBuilder);
        return stringBuilder.toString();
    }

    @Override
    protected String getMethodHeader(BasicControlTypeEnum basicControlTypeEnum, String entityName) {

        StringBuilder sb = new StringBuilder();
        sb.append("public ResultObject ").append(getMethodNameByType(basicControlTypeEnum, entityName));

        if(basicControlTypeEnum.getIsPath().equals(AvailableTypeEnum.YES.getIndex())){
            sb.append("(@PathVariable(\"id\") Integer id)").append("{");
        } else {
            if(basicControlTypeEnum.getCode().toLowerCase().indexOf(PAGE_LABEL) != -1){
                addNewLine(sb, 0, "(@RequestParam(value = \"page\", defaultValue = \"1\", required = false) Integer pageNo,");
                addNewLine(sb, 9, "@RequestParam(value = \"size\", defaultValue = \"10\", required = false) Integer size){");
            }else{
                String paramContent = basicControlTypeEnum.getCode().substring(0, 1).toUpperCase()+basicControlTypeEnum.getCode().substring(1)+entityName+"Req";
                sb.append("(@Valid @RequestBody ").append(paramContent).append(" req)").append("{");
            }
        }


        return sb.toString();
    }

    private String getCallServiceFunc(BasicControlTypeEnum basicControlTypeEnum, String entityName){

        StringBuilder sb = new StringBuilder();

        if(basicControlTypeEnum.getIsPath().equals(AvailableTypeEnum.YES.getIndex())){
            addNewLine(sb, 2, getServiceNameByEntity(entityName) + "." + getMethodNameByType(basicControlTypeEnum, entityName) + "(id);");
            addNewLine(sb, 2, "return ResultStatusEnum.SUCCESS.ok();");
        } else {
            if(basicControlTypeEnum.getCode().toLowerCase().indexOf(PAGE_LABEL) != -1){
                addNewLine(sb, 2, "Page page = new Page<>(pageNo, size);");
                addNewLine(sb, 2, "List<" + entityName + "Rsp> list = " + getServiceNameByEntity(entityName) + ".getBaseMapper().selectByPage(page);");
                addNewLine(sb, 2, "return ResultStatusEnum.SUCCESS.ok(list, page.getTotal());");
            }else{
                addNewLine(sb, 2, getServiceNameByEntity(entityName) + "." + getMethodNameByType(basicControlTypeEnum, entityName) + "(req);");
                addNewLine(sb, 2, "return ResultStatusEnum.SUCCESS.ok();");
            }
        }

        return sb.toString();
    }

    private String getServiceNameByEntity(String entityName){

        return entityName.substring(0,1).toLowerCase() + entityName.substring(1) + "Service";
    }

    private String getMethodNameByType(BasicControlTypeEnum basicControlTypeEnum, String entityName){

        if(basicControlTypeEnum.getChange().equals(ChangeTypeEnum.ENABLE_CHANGE.getIndex())){
            return basicControlTypeEnum.getCode() + entityName;
        }else{
            return basicControlTypeEnum.getCode();
        }
    }
}
