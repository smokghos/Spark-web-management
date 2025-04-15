package com.spark.mapper;

import com.spark.pojo.Clazz;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
@Mapper
public interface ClazzMapper {

    List<Clazz> list(@Param("name") String name, @Param("begin") LocalDate begin, @Param("end") LocalDate end);

    @Select("select * from clazz")
    List<Clazz> findAll();

    @Insert("insert into clazz VALUES (null,#{name},#{room},#{beginDate},#{endDate},#{masterId}, #{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    @Select("SELECT * FROM clazz WHERE id = #{id}")
    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);
}
