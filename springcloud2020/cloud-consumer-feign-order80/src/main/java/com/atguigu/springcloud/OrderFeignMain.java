package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-02-21 20:38
 */

@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain {
    public static void main(String[] args) {
            SpringApplication.run(OrderFeignMain.class, args);
        }
}
