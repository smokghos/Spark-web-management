package com.spark.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.spark.mapper.ClazzMapper;
import com.spark.pojo.Clazz;
import com.spark.pojo.PageResult;
import com.spark.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzMapper clazzMapper;
    @Override
    public PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Clazz> dataList = clazzMapper.list(name,begin,end);
        Page<Clazz> p =(Page<Clazz>) dataList;
        return new PageResult(p.getTotal(),p.getResult());

    }
}
