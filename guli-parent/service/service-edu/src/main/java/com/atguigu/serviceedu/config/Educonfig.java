package com.atguigu.serviceedu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-05-12 1:47
 */

@Configuration
@MapperScan("com.atguigu.serviceedu.mapper")
public class Educonfig {
}
