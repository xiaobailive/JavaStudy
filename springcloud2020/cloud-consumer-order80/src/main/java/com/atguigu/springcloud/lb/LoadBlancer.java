package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-02-21 0:08
 */


public interface LoadBlancer {
    ServiceInstance instances(List<ServiceInstance> serviceinstances);
}
