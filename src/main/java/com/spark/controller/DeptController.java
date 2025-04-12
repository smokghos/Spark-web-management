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
     * 查询部门列表
     */
//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping()
    public Result list() {
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*@DeleteMapping("/depts")
    public Result delete(HttpServletRequest httpServletRequest) {
        String idStr = httpServletRequest.getParameter("id");
        int deptID =Integer.parseInt(idStr);
        System.out.println("根据ID删除部门 " + deptID);
        return Result.success();
    }*/
    @DeleteMapping()
    public Result delete(Integer id) {
        log.info("查询部门列表");
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping()
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门, dept: {}" , dept);
        deptService.add(dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo (@PathVariable Integer id){
        log.info("根据ID查询, id: {}" , id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }
    @PutMapping("/depts")
    public Result update (@RequestBody Dept dept ) {
        log.info("修改部门, dept: {}" , dept);
        deptService.update(dept);
        return Result.success();
    }

}