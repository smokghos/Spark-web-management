package com.spark.controller;

import com.spark.pojo.ClazzCountOption;
import com.spark.pojo.JobOption;
import com.spark.pojo.Result;
import com.spark.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 报表控制器类，用于处理与报表生成相关的HTTP请求。
 * 提供员工职位统计、班级统计等报表数据的查询接口。
 */
@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    /**
     * 报表服务接口的实例，用于调用报表生成相关的方法
     */
    @Autowired
    private ReportService reportService;

    /**
     * 获取员工职位统计数据
     *
     * @return Result 包含员工职位统计数据的成功结果对象
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("获取员工职位统计数据");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 获取员工性别统计数据
     *
     * @return Result 包含员工性别统计数据的成功结果对象
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别人数");
        //TODO List<Map<String, Object>>
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    /**
     * 统计学员的学历信息
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员的学历信息");
        List<Map> dataList = reportService.getStudentDegreeData();
        return Result.success(dataList);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("班级人数统计");
        ClazzCountOption clazzCountOption = reportService.getStudentCountData();
        return Result.success(clazzCountOption);
    }
}
