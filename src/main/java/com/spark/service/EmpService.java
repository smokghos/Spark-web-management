package com.spark.service;

import com.spark.pojo.Emp;
import com.spark.pojo.EmpQueryParam;
import com.spark.pojo.PageResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 使用@Mapper注解标识该接口是一个MyBatis的Mapper接口，MyBatis会自动为该接口生成实现类
@Mapper
public interface EmpService {
    // 定义一个分页查询的方法，接收一个EmpQueryParam类型的参数，返回一个PageResult<Emp>类型的结果
    // EmpQueryParam包含了查询所需的参数，如页码、每页大小、查询条件等
    // PageResult<Emp>是一个分页结果类，包含了查询到的员工信息列表以及分页信息，如总记录数、总页数等
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    // 定义一个保存员工信息的方法，接收一个Emp类型的参数
    // Emp是一个实体类，包含了员工的基本信息，如姓名、职位、薪资等
    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);
    /**
     * 更新员工信息
     */
    void update(Emp emp);
}
