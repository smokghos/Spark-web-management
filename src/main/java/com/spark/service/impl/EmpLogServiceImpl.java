/**
 * 实现员工日志服务接口，提供具体的日志记录操作
 * 该类主要负责处理员工相关操作的日志记录，确保每个操作都有相应的记录
 */
package com.spark.service.impl;

import com.spark.mapper.EmpLogMapper;
import com.spark.pojo.EmpLog;
import com.spark.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实现EmpLogService接口，定义员工日志的具体操作
 * 该类主要职责是将员工操作日志插入到数据库中，以供后续审计或查看
 */
@Service
public class EmpLogServiceImpl implements EmpLogService {

    /**
     * 注入EmpLogMapper接口的实现类，用于操作员工日志数据
     * 通过该mapper可以将日志数据插入数据库
     */
    @Autowired
    private EmpLogMapper empLogMapper;

    /**
     * 插入员工操作日志到数据库中
     * 该方法主要用于在进行员工相关操作时记录操作日志，确保可追溯性和责任明确
     *
     * @param empLog 要插入的员工日志对象，包含日志的所有必要信息
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
