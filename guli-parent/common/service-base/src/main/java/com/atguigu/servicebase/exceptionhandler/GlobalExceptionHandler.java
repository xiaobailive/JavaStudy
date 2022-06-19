package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.DataResult;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public DataResult error(Exception e) {
        e.printStackTrace();
        return DataResult.error().message("执行了全局异常处理..");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public DataResult error(ArithmeticException e) {
        e.printStackTrace();
        return DataResult.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(BaseException.class)
    @ResponseBody //为了返回数据
    public DataResult error(BaseException e) {
        log.error(e.getMessage());
        e.printStackTrace();

        return DataResult.error().code(e.getCode()).message(e.getMsg());
    }

}
