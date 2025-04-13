package com.spark.exception;

import com.spark.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理异常
    @ExceptionHandler
    public Result handleException(Exception e){//方法形参中指定能够处理的异常类型
        //打印堆栈中的异常信息
        log.error("操作失败,错误原因是： " + e);
        //捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员");
    }

}