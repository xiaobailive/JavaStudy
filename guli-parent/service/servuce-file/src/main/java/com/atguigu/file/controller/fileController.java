package com.atguigu.file.controller;

import com.atguigu.commonutils.DataResult;
import com.atguigu.file.service.FileService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-06-06 23:50
 */

@RestController
@RequestMapping("/edufile/fileupload")
@CrossOrigin
public class fileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public DataResult uploadFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = fileService.uploadFileAvatar(file);
        return DataResult.ok().data("url",url);
    }
}
