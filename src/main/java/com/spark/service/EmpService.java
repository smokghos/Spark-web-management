/**
 * EmpService接口提供了员工信息的基本操作，如分页查询、保存、删除和获取信息
 */
package com.spark.service;

import com.spark.pojo.Emp;
import com.spark.pojo.EmpQueryParam;
import com.spark.pojo.LoginInfo;
import com.spark.pojo.PageResult;

import java.util.List;

/**
 * EmpService接口定义了员工信息管理的相关方法
 */
public interface EmpService {
    /**
     * 根据查询参数分页查询员工信息
     *
     * @param empQueryParam 包含了查询所需的参数，如页码、每页大小、查询条件等
     * @return 返回一个分页结果对象，包含查询到的员工信息列表以及分页信息
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 保存员工信息
     *
     * @param emp 一个员工实体对象，包含员工的基本信息，如姓名、职位、薪资等
     */
    void save(Emp emp);

    /**
     * 根据员工ID列表删除员工信息
     *
     * @param ids 一个整数列表，包含要删除的员工的ID
     */
    void delete(List<Integer> ids);

    /**
     * 根据员工ID获取员工信息
     *
     * @param id 员工的唯一标识ID
     * @return 返回一个员工实体对象，包含查询到的员工信息
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     *
     * @param emp 一个员工实体对象，包含要更新的员工信息
     */
    void update(Emp emp);

    List<Emp> list();

    /**
     * 登录
     */
    LoginInfo login(Emp emp);
}
