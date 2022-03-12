package com.atguigu2.preparedstatement.curd;

import java.sql.*;

import org.junit.Test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * Description:
 *使用PreparedStatement来替换Statement,实现对数据表的增删改操作
 * @author 小白
 * @create 2022-03-12 18:12
 */


public class PreparedStatementUpdateTest {

    @Test
    public void testInsert(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.读取配置文件
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);

            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String jdbcClass = pros.getProperty("jdbcClass");

            //2.加载驱动
            Class.forName(jdbcClass);
            //3.获取链接
            conn = DriverManager.getConnection(url,user,password);

            //4.预编译sql语句,返回PreparedStatement的实例
            String sql = "insert into employees(last_name,email,hire_date)values(?,?,?)";
            ps = conn.prepareStatement(sql);
            //5.填充占位符
            ps.setString(1,"xiaobai");
            ps.setString(2,"xiaobai1@qq.com");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = simpleDateFormat.parse("2000-01-01");
            ps.setDate(3,new Date(date.getTime()));
            //6.执行操作
            ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //7.关闭资源
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
