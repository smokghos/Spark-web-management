package com.spark.mapper;

import com.spark.pojo.Emp;
import com.spark.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {


    List<Emp> list( EmpQueryParam empQueryParam);
    /**
     * 新增员工数据
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time," +
            " update_time) values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate}," +
            "#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
    //@Param("ids")使用集合显式指定参数名
    void deleteByIds(@Param("ids") List<Integer> ids);

    Emp getById(Integer id);
    /**
     * 更新员工基本信息
     */
    void updateById(Emp emp);
    /**
     * 统计员工职位人数
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
}

