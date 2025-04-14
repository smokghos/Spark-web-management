package com.spark.mapper;

import com.spark.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
@Mapper
public interface ClazzMapper {

    List<Clazz> list(@Param("name")String name, @Param("begin") LocalDate begin,@Param("end") LocalDate end);
}
