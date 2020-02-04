package com.joe.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joe.code.dto.response.GenerateListRsp;
import com.joe.code.dto.response.SysProjectRsp;
import com.joe.code.entity.SysProject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysProjectMapper extends BaseMapper<SysProject> {

    List<SysProjectRsp> selectByPage(Page<SysProjectRsp> page, @Param("sysName") String sysName);

    List<GenerateListRsp> selectGenerateListByPage(Page<GenerateListRsp> page, @Param("sysName") String sysName);
}
