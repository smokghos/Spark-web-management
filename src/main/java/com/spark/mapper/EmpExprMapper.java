package com.spark.mapper;

import com.spark.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpExprMapper  {

    void insertBatch(@Param("exprList") List<EmpExpr> exprList);

    void deleteByEmpIds(@Param("empIds") List<Integer> empIds);
}
