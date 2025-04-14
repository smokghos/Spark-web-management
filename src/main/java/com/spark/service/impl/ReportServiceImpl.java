package com.spark.service.impl;

import com.spark.mapper.EmpMapper;
import com.spark.pojo.JobOption;
import com.spark.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public JobOption getEmpJobData() {
        // 1. 调用mapper接口, 获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData(); // map: pos=教研主管, num=1

        // 2. 组装结果, 并返回
        List<Object> jobList = list.stream()
                .map(dataMap -> dataMap.get("pos"))
                .collect(Collectors.toList()); // 替换 toList()

        List<Object> dataList = list.stream()
                .map(dataMap -> dataMap.get("num"))
                .collect(Collectors.toList()); // 替换 toList()

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}

