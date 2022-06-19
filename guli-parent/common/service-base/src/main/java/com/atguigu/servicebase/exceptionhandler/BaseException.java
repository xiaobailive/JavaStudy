package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-06-19 23:02
 */


@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class BaseException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}

