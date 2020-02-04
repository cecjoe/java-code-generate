package com.joe.code.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateProjectReq extends AddProjectReq {

    @NotNull(message = "ID不能为空！")
    private Integer id;
}
