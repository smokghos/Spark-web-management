package com.spark.mapper;

import com.spark.pojo.Emp;
import com.spark.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 员工数据映射接口，用于定义与员工相关的数据库操作。
 */
@Mapper
public interface EmpMapper {

    /**
     * 根据查询参数获取员工列表。
     *
     * @param empQueryParam 查询参数对象，包含筛选条件。
     * @return 符合条件的员工列表。
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工数据。
     * 使用自动生成主键并将其赋值给员工对象的 id 属性。
     *
     * @param emp 包含员工信息的对象。
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time," +
            " update_time) values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate}," +
            "#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 根据 ID 批量删除员工记录。
     *
     * @param ids 需要删除的员工 ID 列表。
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据 ID 获取员工信息。
     *
     * @param id 员工的唯一标识符。
     * @return 对应 ID 的员工对象。
     */
    Emp getById(Integer id);

    /**
     * 更新指定 ID 的员工基本信息。
     *
     * @param emp 包含更新信息的员工对象。
     */
    void updateById(Emp emp);

    /**
     * 统计各职位的员工人数。
     * 返回结果以职位名称为键进行映射。
     *
     * @return 各职位及其对应人数的映射列表。
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计各性别的员工人数。
     * 返回结果以性别名称为键进行映射。
     *
     * @return 各性别及其对应人数的映射列表。
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
}
