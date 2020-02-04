package com.joe.code.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.joe.code.common.enums.ResultStatusEnum;
import com.joe.code.common.utils.ResultObject;
import com.joe.code.service.SysTableFieldService;

@RestController
@RequestMapping("/sysTableField")
public class SysTableFieldController {

    @Autowired
    private SysTableFieldService sysTableFieldService;

    /**
    * 分页查询
    */
    @GetMapping("/list")
    public ResultObject selectByPage(@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
                                    @RequestParam(value = "size", defaultValue = "10", required = false) Integer size){

        Page page = new Page<>(pageNo, size);
        List<SysTableFieldRsp> list = sysTableFieldService.getBaseMapper().selectByPage(page);
        return ResultStatusEnum.SUCCESS.ok(list, page.getTotal());
    }

    /**
    * 新增
    */
    @PostMapping("/create")
    public ResultObject addSysTableField(@Valid @RequestBody AddSysTableFieldReq req){
        sysTableFieldService.addSysTableField(req);
        return ResultStatusEnum.SUCCESS.ok();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    public ResultObject updateSysTableField(@Valid @RequestBody UpdateSysTableFieldReq req){
        sysTableFieldService.updateSysTableField(req);
        return ResultStatusEnum.SUCCESS.ok();
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResultObject deleteSysTableField(@PathVariable("id") Integer id){
        sysTableFieldService.deleteSysTableField(id);
        return ResultStatusEnum.SUCCESS.ok();
    }

}
