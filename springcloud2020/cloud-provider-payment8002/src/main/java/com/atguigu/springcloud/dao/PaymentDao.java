package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 *
 * @author bai
 * @create 2022-02-15 20:26
 */

@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public  Payment getPaymentById(@Param("id") Long id);
}
