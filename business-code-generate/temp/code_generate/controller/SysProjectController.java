package com.joe.code.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.joe.code.common.enums.ResultStatusEnum;
import com.joe.code.common.utils.ResultObject;
import com.joe.code.service.SysProjectService;

@RestController
@RequestMapping("/sysProject")
public class SysProjectController {

    @Autowired
    private SysProjectService sysProjectService;

    /**
    * 分页查询项目信息表
    */
    @GetMapping("/list")
    public ResultObject selectByPage(@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
                                    @RequestParam(value = "size", defaultValue = "10", required = false) Integer size){

        Page page = new Page<>(pageNo, size);
        List<SysProjectRsp> list = sysProjectService.getBaseMapper().selectByPage(page);
        return ResultStatusEnum.SUCCESS.ok(list, page.getTotal());
    }

    /**
    * 新增项目信息表
    */
    @PostMapping("/create")
    public ResultObject addSysProject(@Valid @RequestBody AddSysProjectReq req){
        sysProjectService.addSysProject(req);
        return ResultStatusEnum.SUCCESS.ok();
    }

    /**
    * 修改项目信息表
    */
    @PutMapping("/update")
    public ResultObject updateSysProject(@Valid @RequestBody UpdateSysProjectReq req){
        sysProjectService.updateSysProject(req);
        return ResultStatusEnum.SUCCESS.ok();
    }

    /**
    * 删除项目信息表
    */
    @DeleteMapping("/delete/{id}")
    public ResultObject deleteSysProject(@PathVariable("id") Integer id){
        sysProjectService.deleteSysProject(id);
        return ResultStatusEnum.SUCCESS.ok();
    }

}
