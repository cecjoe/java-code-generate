package com.joe.code.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joe.code.entity.SysTableField;
import com.joe.code.mapper.SysTableFieldMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SysTableFieldService extends ServiceImpl<SysTableFieldMapper, SysTableField> {
}
