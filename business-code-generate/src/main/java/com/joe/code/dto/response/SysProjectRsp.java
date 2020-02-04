package com.joe.code.dto.response;

import com.joe.code.common.enums.ProjectStateEnum;
import lombok.Data;

@Data
public class SysProjectRsp {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 系统名称
     */
    private String sysName;

    /**
     * 数据库URL地址
     */
    private String dbUrl;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 数据库连接用户名
     */
    private String userName;

    /**
     * 数据库连接密码
     */
    private String userPass;

    /**
     * 项目主包名
     */
    private String rootPath;

    private Integer state;

    private String stateDesc;

    public String getStateDesc(){

        if(state == null){
            ProjectStateEnum.CREATED.getLabel();
        }

        return ProjectStateEnum.mapData.get(state);
    }
}
