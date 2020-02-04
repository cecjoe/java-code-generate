package com.joe.code.dto.request;

import com.joe.code.common.utils.CommonConstants;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

@Data
public class PreProcessProjectReq {

    @NotNull(message = "ID不能为空！")
    private Integer id;

    private String controlName;

    private String serviceName;

    private String daoName;

    public String getControlName(){
        if(StringUtils.isEmpty(controlName)){
            return CommonConstants.PROJECT_DEFAULT_CONTROL_NAME;
        }

        return controlName;
    }

    public String getServiceName(){
        if(StringUtils.isEmpty(serviceName)){
            return CommonConstants.PROJECT_DEFAULT_SERVICE_NAME;
        }

        return serviceName;
    }

    public String getDaoName(){
        if(StringUtils.isEmpty(daoName)){
            return CommonConstants.PROJECT_DEFAULT_DAO_NAME;
        }

        return daoName;
    }
}
