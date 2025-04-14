package com.spark.service;

import com.spark.pojo.PageResult;

import java.time.LocalDate;

public interface ClazzService {
    PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);
}
