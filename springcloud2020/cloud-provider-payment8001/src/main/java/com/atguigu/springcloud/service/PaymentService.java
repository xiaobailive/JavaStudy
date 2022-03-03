package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 *
 * @author bai
 * @create 2022-02-15 20:49
 */


public interface PaymentService {
    public int create(Payment payment);

    public  Payment getPaymentById(@Param("id") Long id);
}
