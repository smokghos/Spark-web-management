package com.spark.controller;

import com.spark.pojo.Emp;
import com.spark.pojo.EmpQueryParam;
import com.spark.pojo.PageResult;
import com.spark.pojo.Result;
import com.spark.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工信息控制器类，用于处理与员工相关的HTTP请求
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 处理员工信息的分页查询请求
     *
     * @param empQueryParam 包含分页查询条件的参数对象
     * @return 返回员工信息分页查询结果
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询请求参数： {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 处理新增员工信息的请求
     *
     * @param emp 新增员工的信息
     * @return 返回新增结果
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工 {}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 处理删除员工信息的请求
     *
     * @param ids 需要删除的员工ID列表
     * @return 返回删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工信息 " + ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据员工ID查询员工信息
     *
     * @param id 员工ID
     * @return 返回查询到的员工信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据ID查询员工信息 " + id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     *
     * @param emp 需要更新的员工信息
     * @return 返回更新结果
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }

}
