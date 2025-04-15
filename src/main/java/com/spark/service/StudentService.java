package com.spark.service;

import com.spark.pojo.PageResult;
import com.spark.pojo.Student;

import java.util.List;

public interface StudentService {
    PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    void save(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void violationHandle(Integer id, Integer score);

    void delete(List<Integer> ids);
}
