package com.spark.mapper;

import com.spark.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门
     */
    @Select("SELECT id ,name,create_time,update_time FROM dept ORDER BY update_time desc ")
    public List<Dept> findAll();

    @Delete("DELETE FROM dept WHERE id = #{id }")
    void deleteById(Integer id);

    @Insert("INSERT INTO dept (name, create_time, update_time) VALUES (#{name},#{createTime},#{updateTime})")
    void add(Dept dept);
    @Select("SELECT id, name, create_time, update_time FROM dept WHERE id = #{id}")
    Dept getInfo(Integer id);

    @Update("UPDATE dept set name = #{name},update_time =#{updateTime} WHERE id = #{id};")
    void update(Dept dept);
}