package com.joe.code.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joe.code.common.enums.ProjectStateEnum;
import com.joe.code.common.utils.*;
import com.joe.code.dto.request.PreProcessProjectReq;
import com.joe.code.dto.request.TableFieldsQuery;
import com.joe.code.dto.request.TablesQuery;
import com.joe.code.dto.request.UpdateProjectReq;
import com.joe.code.entity.SysProject;
import com.joe.code.entity.SysTableField;
import com.joe.code.entity.SysTableInfo;
import com.joe.code.mapper.SysProjectMapper;
import com.joe.code.mapper.SysTableFieldMapper;
import com.joe.code.mapper.SysTableInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SysProjectService extends ServiceImpl<SysProjectMapper, SysProject> {

    @Autowired
    private SysTableFieldMapper sysTableFieldMapper;

    @Autowired
    private SysTableInfoMapper sysTableInfoMapper;

    @Transactional
    public void updateProject(UpdateProjectReq req){
        SysProject sysProject = getById(req.getId());
        if(sysProject == null){
            throw new IllegalArgumentException("不存在该项目！");
        }

        BeanUtils.copyProperties(req, sysProject);
        updateById(sysProject);
    }

    @Transactional
    public void savePreProcess(PreProcessProjectReq req){

        SysProject sysProject = getById(req.getId());
        if(sysProject == null){
            throw new IllegalArgumentException("项目不存在！");
        }

        BeanUtils.copyProperties(req, sysProject);
        sysProject.setState(ProjectStateEnum.PREPROCESSED.getIndex());
        updateById(sysProject);

        //TODO 查询所有的表和字段，并保存到数据库中
        DynamicConnection dynamicConnection = DynamicConnectionFactory.createDynamicConnection(sysProject.getDbUrl(), sysProject.getUserName(), sysProject.getUserPass());
        DynamicQueryExecutor dynamicQueryExecutor = new DynamicQueryExecutor(dynamicConnection);
        try {
            TablesQuery query = new TablesQuery();
            query.setDbName(sysProject.getDbName());
            List<TableInfo> tablelist = dynamicQueryExecutor.execSQLQuery(CommonConstants.QUERY_ALL_TABLES_BY_DB, query, TableInfo.class);
            if(!CollectionUtils.isEmpty(tablelist)){
                for(TableInfo tableInfo : tablelist){
                    saveSimgleTalble(tableInfo, sysProject.getId(), sysProject.getDbName(), dynamicQueryExecutor);
                }
            }
        }finally {
            dynamicConnection.releaseConnection();
        }

//        dynamicQueryExecutor.execSQLQuery("");


    }

    /**
     * 保存单表的所有定义信息
     */
    private void saveSimgleTalble(TableInfo tableInfo, Integer projectId, String dbName, DynamicQueryExecutor dynamicQueryExecutor){

        SysTableInfo sysTableInfo = new SysTableInfo();
        BeanUtils.copyProperties(tableInfo, sysTableInfo);
        sysTableInfo.setSysProjectId(projectId);
        sysTableInfoMapper.insert(sysTableInfo);
        TableFieldsQuery query = new TableFieldsQuery();
        query.setDbName(dbName);
        query.setTableName(sysTableInfo.getTableName());
        List<TableFieldInfo> tableFieldInfos = dynamicQueryExecutor.execSQLQuery(CommonConstants.QUERY_ALL_FIELDS_BY_TB, query, TableFieldInfo.class);
        for(TableFieldInfo tableFieldInfo : tableFieldInfos){
            SysTableField sysTableField = new SysTableField();
            BeanUtils.copyProperties(tableFieldInfo, sysTableField);
            sysTableField.setSysTableId(sysTableInfo.getId());
            sysTableFieldMapper.insert(sysTableField);
        }
    }
}
