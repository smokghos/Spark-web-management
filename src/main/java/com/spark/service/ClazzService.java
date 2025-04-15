package com.spark.service;

import com.spark.pojo.Clazz;
import com.spark.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    List<Clazz> findAll();

    void save(Clazz clazz);

    void deleteById(Integer id);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);
}
