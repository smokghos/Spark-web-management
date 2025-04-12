package com.spark;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spark.mapper") // 指定Mapper接口的扫描路径
public class SparkWebManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparkWebManagementApplication.class, args);
    }
}

