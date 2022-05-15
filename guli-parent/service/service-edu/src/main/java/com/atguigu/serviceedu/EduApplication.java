package com.atguigu.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-05-12 1:46
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
public class EduApplication {
    public static void main(String[] args) {
            SpringApplication.run(EduApplication.class, args);
        }
}
