package com.spark.controller;

import com.spark.pojo.PageResult;
import com.spark.pojo.Result;
import com.spark.pojo.Student;
import com.spark.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询学生信息
     *
     * @param name 学生姓名
     * @param degree 学历
     * @param clazzId 班级ID
     * @param page 当前页
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @GetMapping
    public Result page(String name , Integer degree, Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询学生信息 ");
        PageResult pageResult = studentService.page(name,degree,clazzId,page,pageSize);
        return Result.success(pageResult);
    }

    /**
     * 保存学生信息
     *
     * @param student 学生对象
     * @return 保存结果
     */
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("保存学生信息 ");
        studentService.save(student);
        return Result.success();
    }

    /**
     * 获取学生信息
     *
     * @param id 学生ID
     * @return 学生信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("获取学生信息 ");
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 更新学生信息
     *
     * @param student 学生对象
     * @return 更新结果
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新学生信息 ");
        studentService.update(student);
        return Result.success();
    }

    /**
     * 处理学生违规行为
     *
     * @param id 学生ID
     * @param score 扣分分数
     * @return 处理结果
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id , @PathVariable Integer score){
        log.info("处理学生违规行为 ");
        studentService.violationHandle(id, score);
        return Result.success();
    }

    /**
     * 删除学生信息
     *
     * @param ids 学生ID列表
     * @return 删除结果
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除学生信息 ");
        studentService.delete(ids);
        return Result.success();
    }

}
