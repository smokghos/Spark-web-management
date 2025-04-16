package com.spark.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录成功结果封装类。
 * 包含登录用户的ID、用户名、姓名以及令牌信息。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private Integer id; //员工ID
    private String username; //用户名
    private String name; //姓名
    private String token; //令牌
}
