package com.spark.controller;

import com.spark.pojo.Clazz;
import com.spark.pojo.PageResult;
import com.spark.pojo.Result;
import com.spark.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 班级控制器类，用于处理与班级相关的HTTP请求。
 * 提供班级的增删改查接口，支持分页查询和条件筛选。
 */
@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    /**
     * 注入班级服务类，用于执行班级相关的业务逻辑
     */
    @Autowired
    private ClazzService clazzService;

    /**
     * 根据条件分页查询班级信息
     *
     * @param name 班级名称
     * @param begin 开始日期
     * @param end 结束日期
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return 分页查询结果
     */
    @GetMapping
    public Result page(String name,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("根据条件分页查询班级信息 ");
        PageResult pageResult = clazzService.page(name, begin, end, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 查询全部班级信息
     *
     * @return 全部班级信息列表
     */
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询全部班级信息 ");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

    /**
     * 添加新的班级信息
     *
     * @param clazz 新班级的信息
     * @return 添加结果
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加新的班级信息 ");
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * 根据ID删除班级信息
     *
     * @param id 要删除的班级的ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据ID删除班级信息 " + id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据ID获取班级信息
     *
     * @param id 要查询的班级的ID
     * @return 班级信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID获取班级信息" + id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }
    /**
     * 更新班级信息
     * @param clazz 要更新的班级对象
     * @return 更新结果
     */
    @PutMapping
    public  Result update(@RequestBody Clazz clazz){
        log.info("更新班级信息 ");
        clazzService.update(clazz);
        return Result.success();
    }
}
