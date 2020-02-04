//package com.joe.code.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import org.springframework.web.bind.annotation.*;
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.joe.code.common.enums.ResultStatusEnum;
//import com.joe.code.common.utils.ResultObject;
//import com.joe.code.service.SysTableInfoService;
//
//@RestController
//@RequestMapping("/sysTableInfo")
//public class SysTableInfoController {
//
//    @Autowired
//    private SysTableInfoService sysTableInfoService;
//
//    /**
//     * 分页查询
//     */
//    @GetMapping("/list")
//    public ResultObject selectByPage(@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
//                                     @RequestParam(value = "size", defaultValue = "10", required = false) Integer size){
//
//        Page page = new Page<>(pageNo, size);
//        List<SysTableInfoRsp> list = sysTableInfoService.getBaseMapper().selectByPage(page);
//        return ResultStatusEnum.SUCCESS.ok(list, page.getTotal());
//    }
//
//    /**
//     * 新增
//     */
//    @PostMapping("/create")
//    public ResultObject addSysTableInfo(@Valid @RequestBody AddSysTableInfoReq req){
//        sysTableInfoService.addSysTableInfo(req);
//        return ResultStatusEnum.SUCCESS.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @PutMapping("/update")
//    public ResultObject updateSysTableInfo(@Valid @RequestBody UpdateSysTableInfoReq req){
//        sysTableInfoService.updateSysTableInfo(req);
//        return ResultStatusEnum.SUCCESS.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResultObject deleteSysTableInfo(@PathVariable("id") Integer id){
//        sysTableInfoService.deleteSysTableInfo(id);
//        return ResultStatusEnum.SUCCESS.ok();
//    }
//
//}
