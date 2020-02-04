package com.joe.code.dto.response;

import com.joe.code.common.enums.ProjectStateEnum;
import lombok.Data;

@Data
public class GenerateListRsp {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 系统名称
     */
    private String sysName;

    /**
     * 项目主包名
     */
    private String rootPath;

    private Integer state;

    private String generateStateDesc;

    /**
     * 编译次数
     */
    private Integer generateTimes;

    public String getGenerateStateDesc(){

        if(state.equals(ProjectStateEnum.CODE_GENERATED.getIndex())){
            return "已编译";
        }else{
            return "待编译";
        }
    }

    public Integer getGenerateTimes(){
        if(generateTimes == null){
            return 0;
        }
        return generateTimes;
    }
}
