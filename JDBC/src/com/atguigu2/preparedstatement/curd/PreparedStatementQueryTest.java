package com.atguigu2.preparedstatement.curd;

import com.atguigu.util.JDBCUtils;
import com.atguigu2.bean.Employee;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:使用PreparedStatement实现针对于不同表的通用的查询操作
 *
 * @author 小白
 * @create 2022-03-12 23:14
 */


public class PreparedStatementQueryTest {

    @Test
    public void testGetInstance() {
//        String sql = "select last_name,email,hire_date from employees where employee_id = ?";
//        Employee employee = getInstance(Employee.class,sql,101);
//        System.out.println(employee);

        String sql1 = "select last_name,email,hire_date from employees where employee_id < ?";
        List<Employee> list = getForList(Employee.class, sql1, 110);
        list.forEach(System.out::println);
    }

    /**
     * 针对于不同的表的通用的查询操作，返回表中的一条记录
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resource = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            resource = ps.executeQuery();
            // 获取结果集的原数据 ResultSetMetaData
            ResultSetMetaData resultMetdata = resource.getMetaData();
            // 通过ResultMetaData 获取结果集重的列数
            int columCount = resultMetdata.getColumnCount();

            if (resource.next()) { //如果结果集有数据
                T t = clazz.newInstance();
                for (int i = 0; i < columCount; i++) { //处理每一行中的
                    //获取列值
                    Object columValue = resource.getObject(i + 1);
                    //获取每个列的列名 用于set属性 通过反射
                    //获取列的列名：getColumnName() --不推荐使用
                    //获取列的别名：getColumnLabel()
//					String columnName = resultMetdata.getColumnName(i + 1);
                    String columnLabel = resultMetdata.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, resource);
        }
        return null;
    }

    /**
     * 针对于不同的表的通用的查询操作，返回表中的多条记录
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resource = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            resource = ps.executeQuery();
            // 获取结果集的原数据 ResultSetMetaData
            ResultSetMetaData resultMetdata = resource.getMetaData();
            // 通过ResultMetaData 获取结果集重的列数
            int columCount = resultMetdata.getColumnCount();
            ArrayList<T> list = new ArrayList<T>();
            while (resource.next()) { //如果结果集有数据
                T t = clazz.newInstance();
                for (int i = 0; i < columCount; i++) { //处理每一行中的
                    //获取列值
                    Object columValue = resource.getObject(i + 1);
                    //获取每个列的列名 用于set属性 通过反射
                    //获取列的列名：getColumnName() --不推荐使用
                    //获取列的别名：getColumnLabel()
//					String columnName = resultMetdata.getColumnName(i + 1);
                    String columnLabel = resultMetdata.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, resource);
        }
        return null;
    }
}
