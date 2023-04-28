package com.ksd.pug.controller;

import com.ksd.pug.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class PageController {

    private final IUserService userService;


    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/toLogin")
    public String showLogin(){
        return "login";
    }

    /**
     * 登录成功后跳转首页
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex() {
        return "redirect:/index";
    }

    /**
     * 失败后跳转页面
     *
     * @return
     */
    @RequestMapping("/toError")
    public String toError() {
        return "redirect:/error.html";
    }
}
