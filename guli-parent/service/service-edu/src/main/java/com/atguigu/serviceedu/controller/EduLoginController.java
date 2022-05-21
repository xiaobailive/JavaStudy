package com.atguigu.serviceedu.controller;

import com.atguigu.commonutils.DataResult;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author 小白
 * @create 2022-05-21 13:40
 */

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public DataResult login() {
        return DataResult.ok().data("token", "admin");
    }

    @GetMapping("info")
    public DataResult info() {
        return DataResult.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01ae575bf77614a8012092529ce4cc.gif&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655703891&t=9e2ecd283efb8247b11e279d9b08e4fe");
    }
}
