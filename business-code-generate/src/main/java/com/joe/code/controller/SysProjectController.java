package com.joe.code.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joe.code.common.enums.ProjectStateEnum;
import com.joe.code.common.enums.ResultStatusEnum;
import com.joe.code.common.utils.ResultObject;
import com.joe.code.dto.request.AddProjectReq;
import com.joe.code.dto.request.UpdateProjectReq;
import com.joe.code.dto.response.SysProjectRsp;
import com.joe.code.entity.SysProject;
import com.joe.code.service.SysProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sysProject")
public class SysProjectController {

    @Autowired
    private SysProjectService sysProjectService;

    @GetMapping("/list")
    public ResultObject<List<SysProjectRsp>> selectListByPage(@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
                                                                  @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                                                  @RequestParam(value = "sysName", required = false) String sysName){

        Page page = new Page<>(pageNo, size);
        List<SysProjectRsp> list = sysProjectService.getBaseMapper().selectByPage(page, sysName);
        return ResultStatusEnum.SUCCESS.ok(list, page.getTotal());
    }

    /**
     * 创建项目
     * @param req
     * @return
     */
    @PostMapping("/create")
    public ResultObject addProject(@Valid @RequestBody AddProjectReq req){

        SysProject sysProject = new SysProject();
        BeanUtils.copyProperties(req, sysProject);
        sysProject.setState(ProjectStateEnum.CREATED.getIndex());
        sysProjectService.save(sysProject);
        return ResultStatusEnum.SUCCESS.ok();
    }

    /**
     * 更新项目
     * @param req
     * @return
     */
    @PutMapping("/update")
    public ResultObject updateProject(@Valid @RequestBody UpdateProjectReq req){

        sysProjectService.updateProject(req);
        return ResultStatusEnum.SUCCESS.ok();
    }

    /**
     * 根据ID删除项目信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultObject deleteProject(@PathVariable("id") Integer id){

        sysProjectService.removeById(id);
        return ResultStatusEnum.SUCCESS.ok();
    }


}
