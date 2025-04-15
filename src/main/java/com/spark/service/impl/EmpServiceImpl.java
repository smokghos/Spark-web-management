package com.spark.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.spark.mapper.EmpExprMapper;
import com.spark.mapper.EmpMapper;
import com.spark.pojo.*;
import com.spark.service.EmpLogService;
import com.spark.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 员工服务实现类
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    /**
     * 分页查询员工信息
     *
     * @param empQueryParam 分页查询参数对象，包含查询条件、页码和每页大小
     * @return 返回分页结果对象，包含总记录数和员工信息列表
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. 设置PageHelper分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3. 封装分页结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(), p.getResult());
    }

    /**
     * 保存员工信息，包括基本信息和工作经历
     *
     * @param emp 员工信息对象，包含基本信息和工作经历列表
     * @throws Exception 数据库操作异常
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        }finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }
    }

    /**
     * 删除员工信息，包括基本信息和工作经历
     *
     * @param ids 员工ID列表，用于指定要删除的员工
     * @throws Exception 数据库操作异常
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //根据员工ID删除基本信息
        empMapper.deleteByIds(ids);
        //根据员工id删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);

    }

    /**
     * 根据员工ID获取员工详细信息
     *
     * @param id 员工ID，用于指定要获取信息的员工
     * @return 返回员工信息对象，包含基本信息和工作经历列表
     */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 更新员工信息，包括基本信息和工作经历
     *
     * @param emp 员工信息对象，包含要更新的基本信息和工作经历列表
     * @throws Exception 数据库操作异常
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //3. 新增员工的工作经历数据 【新增新的】
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        return empMapper.findAll();
    }
}
