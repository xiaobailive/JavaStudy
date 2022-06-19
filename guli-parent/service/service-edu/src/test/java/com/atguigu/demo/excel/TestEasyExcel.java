package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 生成Excel 写操作测试
 * @author 小白
 * @create 2022-06-19 21:36
 */


public class TestEasyExcel {
    public static void main(String[] args) {
        // 写法1
        String fileName = "D:\\Logs\\11.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(data());
    }

    @Test
    public void wirtExcel02(){
        // 写法2，方法二需要手动关闭流
        String fileName = "D:\\Logs\\Test02.xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("写入方法二").build();
        excelWriter.write(data(), writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }
    @Test
    public void readExcel01(){
        // 写法1：
        String fileName = "D:\\Logs\\Test02.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }
    @Test
    public void readExcel02(){
        // 写法2：
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("D:\\Logs\\Test02.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelReader excelReader = EasyExcel.read(in, DemoData.class, new ExcelListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    // 塞数据
    //循环设置要添加的数据，最终封装到list集合中
    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }
}
