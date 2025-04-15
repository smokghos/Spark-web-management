package com.spark.service.impl;

import com.spark.mapper.EmpMapper;
import com.spark.mapper.StudentMapper;
import com.spark.pojo.ClazzCountOption;
import com.spark.pojo.JobOption;
import com.spark.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
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

    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzCountOption getStudentCountData() {
        List<Map<String, Object>> countList = studentMapper.getStudentCount();
        if(!CollectionUtils.isEmpty(countList)){
            // 从每个Map中提取班级名称并收集到列表中
            List<Object> clazzList = countList.stream()
                    .map(map -> map.get("cname"))
                    .collect(Collectors.toList());

            // 从每个Map中提取学生数量并收集到列表中
            List<Object> dataList = countList.stream()
                    .map(map -> map.get("scount"))
                    .collect(Collectors.toList());

            return new ClazzCountOption(clazzList, dataList);
        }
        return null;
    }

}

