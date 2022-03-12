package com.atguigu1.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-03-11 2:04
 */


public class ConnectionTest {

    @Test
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3307/atguigudb";
        //将用户名和密码封装在Properties中

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");


        Connection conn = driver.connect(url, info);

        System.out.println(conn);
    }

    //方式二 对方式一的迭代 不使用第三方api 使程序有更好的移植性
    @Test
    public void testConnection2() throws Exception {
        // 使用反射获取实例
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //提供链接的数据库
        String url = "jdbc:mysql://localhost:3307/atguigudb";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");

        //获取链接
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    @Test
    public void testConnection3() throws Exception {
        // 使用反射获取实例
        String url = "jdbc:mysql://localhost:3307/atguigudb";
        String user = "root";
        String password = "123456";
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //注册驱动可以省略 因为在mysql的Driver实现类中 有静态代码块实现了注册
        //DriverManager.registerDriver(driver);
        Connection conn =DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }


    @Test //通过配置文件链接
    public void testConnection4() throws Exception {
        //1.获取类的加载器 获取输入流用于读取文件\
        ClassLoader classLoader = ConnectionTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String jdbcClass = pros.getProperty("jdbcClass");
        System.out.println(user+password);
        //注册省略 直接加载驱动
        Class.forName(jdbcClass);

        Connection conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }
}

