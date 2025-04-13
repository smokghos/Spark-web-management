package com.spark.mapper;

import com.spark.pojo.Emp;
import com.spark.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

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
}


/*
    @Select("select emp.*,dept.name as deptName from emp left join dept on emp.dept_id = dept.id" +
            " order by emp.update_time  desc ")
    List<Emp> list();
    @Select("SELECT count(*) FROM emp left join dept on emp.dept_id = dept.id")
     Long count();
    多个参数一定的加param注解！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    @Select("select emp.*,dept.name as deptName from emp left join dept on emp.dept_id = dept.id order by emp.update_time  desc limit #{start},#{pageSize}")
    List<Emp> list(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    @Select("SELECT emp.id, emp.username, emp.password, emp.name," +
            " emp.gender, emp.phone, emp.job, emp.salary, emp.image, " +
            "emp.entry_date, emp.dept_id, emp.create_time, emp.update_time, dept.name as deptName " +
            "FROM emp LEFT JOIN dept ON emp.dept_id = dept.id " +
            "ORDER BY emp.update_time DESC LIMIT #{start}, #{pageSize}")
    List<Emp> list(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

        /**
         * 查询所有的员工及其对应的部门名称
         */
//    @Select("select e.*, d.name as deptName from emp e left join dept d on e.dept_id = d.id")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);



