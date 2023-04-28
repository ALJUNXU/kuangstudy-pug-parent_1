package com.ksd.pug.controller;

import com.ksd.pug.service.user.IUserService;
import com.pug.commons.anno.PugLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/1 23:35
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final IUserService userService;


    @GetMapping("/login")
    @PugLog
    public String login() {
        return "login";
    }
}
