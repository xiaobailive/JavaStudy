package com.atguigu.file.service.impl;

import com.atguigu.file.service.FileService;
import com.atguigu.file.utils.UploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.joda.time.DateTime;


import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-06-06 23:57
 */

@Service
public class FileServiceImpl implements FileService {

    //上传头像到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        try {
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            // yuy76t5rew01.jpg
            fileName = uuid+fileName;
            //2 把文件按照日期进行分类
            //获取当前日期
            //   2019/11/12
            /*String datePath = new DateTime().toString("yyyy/MM/dd");*/
            //拼接
            //  2019/11/12/ewtqr313401.jpg
            /*fileName = datePath+"/"+fileName;*/

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            boolean flag = UploadUtil.uploadFile(fileName, inputStream);
            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //  https://edu-guli-1010.oss-cn-beijing.aliyuncs.com/01.jpg
            if(flag) {
                // 上传文件成功后 返回文件路径
                String url = "https://neko33.cc/file/"+fileName;
                return url;
            } else {
                return "上传失败";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
