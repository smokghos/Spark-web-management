package com.spark.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.spark.exception.BusinessException;
import com.spark.mapper.ClazzMapper;
import com.spark.mapper.StudentMapper;
import com.spark.pojo.Clazz;
import com.spark.pojo.PageResult;
import com.spark.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 班级服务实现类
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询班级信息
     *
     * @param name 班级名称
     * @param begin 开始日期
     * @param end 结束日期
     * @param page 当前页码
     * @param pageSize 页面大小
     * @return 分页结果
     */
    @Override
    public PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Clazz> dataList = clazzMapper.list(name, begin, end);
        Page<Clazz> p = (Page<Clazz>) dataList;
        return new PageResult(p.getTotal(), p.getResult());
    }

    /**
     * 查询所有班级信息
     *
     * @return 班级列表
     */
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    /**
     * 保存班级信息
     *
     * @param clazz 班级对象
     */
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 根据ID删除班级信息
     *
     * @param id 班级ID
     */
    @Override
    public void deleteById(Integer id) {
        Integer count = studentMapper.countByClazzId(id);
        if (count > 0){
            throw new BusinessException("班级下有学员, 不能直接删除~");
        }
        clazzMapper.deleteById(id);
    }

    /**
     * 根据ID获取班级信息
     *
     * @param id 班级ID
     * @return 班级对象
     */
    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getInfo(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }
}
