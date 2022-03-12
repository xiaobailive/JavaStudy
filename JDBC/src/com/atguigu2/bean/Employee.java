package com.atguigu2.bean;

import java.sql.Date;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-03-13 1:10
 */


public class Employee {
    private String last_name;
    private String email;
    private Date hire_date;


    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", hire_date=" + hire_date +
                '}';
    }
}
