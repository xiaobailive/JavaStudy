package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.DataResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-05-15 14:50
 */

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DataResult error(Exception e){
        e.printStackTrace();
        return DataResult.error().message("执行了全局异常处理==>"+ e.getMessage());
    }
}
