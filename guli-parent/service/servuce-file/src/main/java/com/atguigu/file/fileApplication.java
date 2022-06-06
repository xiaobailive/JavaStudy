package com.atguigu.file;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-06-06 23:10
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.atguigu"})
public class fileApplication {
    public static void main(String[] args) {
        SpringApplication.run(fileApplication.class, args);
    }
}
