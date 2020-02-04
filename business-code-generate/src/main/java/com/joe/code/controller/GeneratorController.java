package com.joe.code.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joe.code.common.configuration.ConstantConfiguration;
import com.joe.code.common.enums.ProjectStateEnum;
import com.joe.code.common.enums.ResultStatusEnum;
import com.joe.code.common.utils.EntityCodeUtil;
import com.joe.code.common.utils.PageResultObject;
import com.joe.code.common.utils.ResultObject;
import com.joe.code.common.utils.ZipUtils;
import com.joe.code.dto.request.PreProcessProjectReq;
import com.joe.code.dto.response.GenerateListRsp;
import com.joe.code.entity.SysProject;
import com.joe.code.entity.SysTableInfo;
import com.joe.code.service.CodeGeneratorService;
import com.joe.code.service.SysProjectService;
import com.joe.code.service.SysTableInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

@RestController
@Slf4j
public class GeneratorController {

    @Autowired
    private SysProjectService sysProjectService;

    @Autowired
    private ConstantConfiguration constantConfiguration;

    @Autowired
    private SysTableInfoService sysTableInfoService;

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    /**
     * 保存预处理项目信息
     * @return
     */
    @PutMapping("/project/savePreProcess")
    public ResultObject savePreProcess(@Valid @RequestBody PreProcessProjectReq req){
        sysProjectService.savePreProcess(req);
        return ResultStatusEnum.SUCCESS.ok();
    }

    @RequestMapping("/generate/generateCode/{id}")
    public ResultObject generateCode(@PathVariable("id") Integer id) throws IOException {

        SysProject sysProject = sysProjectService.getById(id);

        //验证项目是否可进行编译
        checkProjectIsRight(sysProject);

        //生成代码文件
        convertContainCodeFile(sysProject);

        //更新项目状态为已编译
        updateStateByProject(sysProject, ProjectStateEnum.CODE_GENERATED);

        return ResultStatusEnum.SUCCESS.ok();
    }

    private void updateStateByProject(SysProject sysProject, ProjectStateEnum projectStateEnum){
        sysProject.setState(projectStateEnum.getIndex());
        sysProject.setGenerateTimes(sysProject.getGenerateTimes() == null ? 1 : (sysProject.getGenerateTimes()+1));
        sysProjectService.updateById(sysProject);
    }

    private void convertContainCodeFile(SysProject sysProject) throws IOException {
        QueryWrapper<SysTableInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sys_project_id", sysProject.getId());
//        queryWrapper
//                .isNull("name")
//                .ge("age", 12)
//                .isNotNull("email");
        List<SysTableInfo> tableInfos = sysTableInfoService.list(queryWrapper);

        //创建必要的文件夹
        String rootPath = constantConfiguration.getFileCreateTmpPath()+ File.separator+sysProject.getDbName();

        String controlPath = rootPath+File.separator+sysProject.getControlName();
        String servicePath = rootPath+File.separator+sysProject.getServiceName();
        String daoPath = rootPath+File.separator+sysProject.getDaoName();
        String dtoReqPath = rootPath+File.separator+"dto"+File.separator+"request";
        String dtoRspPath = rootPath+File.separator+"dto"+File.separator+"response";
        String entityPath = rootPath+File.separator+"entity";
        String daoXmlPath = rootPath+File.separator+sysProject.getDaoName()+File.separator+"xml";
        File controlPathFile = new File(controlPath);
        File servicePathFile = new File(servicePath);
        File daoPathFile = new File(daoPath);
        File dtoReqPathFile = new File(dtoReqPath);
        File dtoRspPathFile = new File(dtoRspPath);
        File entityPathFile = new File(entityPath);
        File daoXmlPathFile = new File(daoXmlPath);
        if(!controlPathFile.exists()){
            controlPathFile.mkdirs();
        }

        if(!servicePathFile.exists()){
            servicePathFile.mkdirs();
        }

        if(!daoPathFile.exists()){
            daoPathFile.mkdirs();
        }

        if(!entityPathFile.exists()){
            entityPathFile.mkdirs();
        }

        if(!dtoReqPathFile.exists()){
            dtoReqPathFile.mkdirs();
        }

        if(!dtoRspPathFile.exists()){
            dtoRspPathFile.mkdirs();
        }

        if(!daoXmlPathFile.exists()){
            daoXmlPathFile.mkdirs();
        }

        for(SysTableInfo tableInfo : tableInfos){

            //生成entity文件
            createEntityFile(entityPath, tableInfo, sysProject.getRootPath() + ".entity");

            //生成Controller文件
            createControlFile(controlPath, tableInfo, sysProject.getRootPath() + "." + sysProject.getControlName());

            createDtoReqFile(dtoReqPath, tableInfo, sysProject.getRootPath() + ".dto.request");

            createDtoRspFile(dtoRspPath, tableInfo, sysProject.getRootPath() + ".dto.response");
//
            //生成Service文件
            createServiceFile(servicePath, tableInfo, sysProject.getRootPath() + "." + sysProject.getServiceName());
//
//            //生成Dao文件
            createDaoFile(daoPath, tableInfo, sysProject.getRootPath() + "." + sysProject.getDaoName());

            //生成Dao xml文件
            createDaoXmlFile(daoXmlPath, tableInfo, sysProject.getRootPath() + "." + sysProject.getDaoName());
        }

        File zipFile = new File(rootPath + "/" + sysProject.getDbName() + ".zip");
        if(zipFile.exists()){
            zipFile.delete();
        }
    }

    /**
     * 删除文件下所有文件夹和文件
     * file：文件名
     * */
//    public static void deleteFileAll(String filePath) {
//        File file = new File(filePath);
//        log.info("path: "+filePath);
//        if (file.exists()) {
//            if(file.isDirectory()){
//                File files[] = file.listFiles();
//                int len = files.length;
//                for (int i = 0; i < len; i++) {
//                    if (files[i].isDirectory()) {
//                        deleteFileAll(files[i].getPath());
//                    } else {
//                        files[i].delete();
//                    }
//                }
//            }
//
//            file.delete();
//        }
//    }

    private void createEntityFile(String entityPath, SysTableInfo tableInfo, String packageName) throws IOException {

        String fileName = EntityCodeUtil.getEntityNameByTable(tableInfo.getTableName()) + ".java";
        String content = codeGeneratorService.getEntityContent(tableInfo, packageName);
        createAndWriteFile(entityPath, fileName, content);
    }

    //生成Controller文件
    private void createControlFile(String controlPath, SysTableInfo tableInfo, String packageName) throws IOException {

        String fileName = EntityCodeUtil.getEntityNameByTable(tableInfo.getTableName()) + "Controller.java";
        String content = codeGeneratorService.getControlContent(tableInfo, packageName);
        createAndWriteFile(controlPath, fileName, content);
    }

    private void createDtoReqFile(String controlPath, SysTableInfo tableInfo, String packageName) throws IOException {

        String fileName = EntityCodeUtil.getEntityNameByTable(tableInfo.getTableName()) + "Req.java";
//        String content = codeGeneratorService.getControlContent(tableInfo, packageName);
        createAndWriteFile(controlPath, fileName, "123");
    }

    private void createDtoRspFile(String controlPath, SysTableInfo tableInfo, String packageName) throws IOException {

        String fileName = EntityCodeUtil.getEntityNameByTable(tableInfo.getTableName()) + "View.java";
//        String content = codeGeneratorService.getControlContent(tableInfo, packageName);
        createAndWriteFile(controlPath, fileName, "123");
    }

    //生成Service文件
    private void createServiceFile(String servicePath, SysTableInfo tableInfo, String packageName) throws IOException{

        String fileName = EntityCodeUtil.getEntityNameByTable(tableInfo.getTableName()) + "Service.java";
        String content = codeGeneratorService.getServiceContent(tableInfo, packageName);
        createAndWriteFile(servicePath, fileName, content);
    }

    //生成Dao文件
    private void createDaoFile(String daoPath, SysTableInfo tableInfo, String packageName) throws IOException {

        String fileName = EntityCodeUtil.getEntityNameByTable(tableInfo.getTableName()) + "Mapper.java";
        String content = codeGeneratorService.getDaoContent(tableInfo, packageName);
        createAndWriteFile(daoPath, fileName, content);
    }

    private void createDaoXmlFile(String daoPath, SysTableInfo tableInfo, String packageName) throws IOException {

        String fileName = EntityCodeUtil.getEntityNameByTable(tableInfo.getTableName()) + "Mapper.xml";
        String content = codeGeneratorService.getDaoXmlContent(tableInfo, packageName);
        createAndWriteFile(daoPath, fileName, content);
    }

    private void createAndWriteFile(String path, String fileName, String content) throws IOException {
        FileChannel inChannel = null;
        File entityFile = new File(path + File.separator + fileName);
        if(entityFile.exists()){
            inChannel = new FileOutputStream(entityFile, false).getChannel();
            entityFile.delete();
        }else{
            entityFile.createNewFile();
            inChannel = new FileOutputStream(entityFile, true).getChannel();
        }

//            FileInputStream fis = new FileInputStream(entityFile);
//        FileChannel inChannel = new FileOutputStream(entityFile, true).getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024*10);

        buf.put(content.getBytes());
        buf.flip();   // 切换为读模式

        while(buf.hasRemaining()) {
            inChannel.write(buf);
        }
    }

    private void checkProjectIsRight(SysProject sysProject){

        if(sysProject == null){
            throw new IllegalArgumentException("不存在此项目！");
        }

        if(sysProject.getState() < ProjectStateEnum.PREPROCESSED.getIndex()){
            throw new IllegalArgumentException("未进行预处理不能编译！");
        }
    }

    /**
     * 获取编译代码状态
     * @return
     */
    @GetMapping("/generate/status/{id}")
    public ResultObject getGenerateStatusByProject(@PathVariable("id") Integer id){

        SysProject sysProject = sysProjectService.getById(id);
        if(sysProject != null){
            return ResultStatusEnum.SUCCESS.ok(sysProject.getState());
        }else{
            return ResultStatusEnum.FAIL.backMsg("项目不存在");
        }
    }

    @GetMapping("/generate/list")
    public PageResultObject<List<GenerateListRsp>> selectListByPage(@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
                                                                  @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                                                  @RequestParam(value = "sysName", required = false) String sysName){

        Page page = new Page<>(pageNo, size);
        List<GenerateListRsp> list = sysProjectService.getBaseMapper().selectGenerateListByPage(page, sysName);
        return ResultStatusEnum.SUCCESS.ok(list, page.getTotal());
    }

    @GetMapping("/generate/download/{id}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable("id") Integer id) {

        SysProject sysProject = sysProjectService.getById(id);
        if(sysProject == null){
            throw new IllegalArgumentException("项目不存在！");
        }

        String dpath = "";
        String dname = "";
        boolean isfail = false;
        String msg = "";

            String zipname = sysProject.getDbName();
        String rootPath = constantConfiguration.getFileCreateTmpPath()+File.separator+sysProject.getDbName();

            dpath = rootPath+File.separator+zipname;
            dname = zipname;

        //压缩文件
        try {
            ZipUtils.folderToZip(rootPath, rootPath, sysProject.getDbName());
        } catch (Exception exp) {
            log.error("压缩文件出现异常 :", exp);
            isfail = true;
            throw new IllegalArgumentException("压缩文件出现异常 :"+exp);
        }

        log.info("下载文件路径及名称:" + dpath + " " + dname);

        if (isfail) {
            log.info("下载失败" + msg);
            return null;
        }

        return this.export(new File(dpath+".zip"));
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }
}
