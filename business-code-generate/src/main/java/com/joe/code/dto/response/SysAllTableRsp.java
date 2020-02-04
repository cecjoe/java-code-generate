package com.joe.code.dto.response;

import com.joe.code.entity.SysTableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysAllTableRsp {

    /**
     * ID主键
     */
    private Integer id;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableRemark;

    /**
     * 项目ID
     */
    private Integer sysProjectId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private List<SysTableField> fieldList;
}
