package com.spark.exception;

import com.spark.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器类，用于捕获和处理项目中的异常。
 * 统一返回错误信息，提升系统的健壮性和用户体验。
 */
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
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException exception){
        log.error("程序出错啦 " + exception);
        String message = exception.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMessage = message.substring(i);
        String[] arr = errMessage.split(" ");
        return Result.error(arr[2] + "已存在");
    }

}