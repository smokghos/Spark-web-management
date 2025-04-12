package com.spark.service;

import com.spark.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     */
    public List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept getInfo(Integer id);

    void update(Dept dept);
}