package com.spark.mapper;

import com.spark.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EmpExprMapper是一个用于处理员工表达式数据的Mapper接口
 * 它定义了批量插入员工表达式数据和根据员工ID列表删除员工表达式数据的方法
 */
@Mapper
public interface EmpExprMapper  {

    /**
     * 批量插入员工表达式数据
     *
     * @param exprList 一个包含多个EmpExpr对象的列表，用于批量插入
     */
    void insertBatch(@Param("exprList") List<EmpExpr> exprList);

    /**
     * 根据员工ID列表删除员工表达式数据
     *
     * @param empIds 一个包含多个员工ID的列表，用于指定要删除的记录
     */
    void deleteByEmpIds(@Param("empIds") List<Integer> empIds);
}
