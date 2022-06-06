package com.atguigu.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-06-06 23:56
 */


public interface FileService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
