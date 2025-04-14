package com.spark.controller;

import com.spark.pojo.Dept;
import com.spark.pojo.Result;
import com.spark.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 获取部门列表
     *
     * @return 部门列表
     */
    @GetMapping()
    public Result list() {
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     *
     * @param id 部门ID
     * @return 操作结果
     */
    @DeleteMapping()
    public Result delete(Integer id) {
        log.info("查询部门列表");
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     *
     * @param dept 部门对象
     * @return 操作结果
     */
    @PostMapping()
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门, dept: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门信息
     *
     * @param id 部门ID
     * @return 部门信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据ID查询, id: {}", id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    /**
     * 修改部门信息
     *
     * @param dept 部门对象
     * @return 操作结果
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门, dept: {}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
