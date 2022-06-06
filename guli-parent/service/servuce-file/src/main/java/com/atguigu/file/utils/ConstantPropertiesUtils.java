package com.atguigu.file.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description:
 * 常量类
 *
 * @author 小白
 * @create 2022-06-06 23:33
 */

//当项目已启动，spring接口，spring加载之后，执行接口一个方法
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.server.username}")
    private String username;

    @Value("${aliyun.server.pswd}")
    private String password;

    @Value("${aliyun.server.domain}")
    private String domainname;

    @Value("${aliyun.server.port}")
    private int prot;

    public static String USER_NAME;
    public static String PASSWORD;
    public static String DOMAIN;
    public static int PROT;

    @Override
    public void afterPropertiesSet() throws Exception {
        USER_NAME = username;
        PASSWORD = password;
        DOMAIN = domainname;
        PROT = prot;
    }

    ;
}
