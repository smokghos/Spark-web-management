package com.spark.service;

import com.spark.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();
}
