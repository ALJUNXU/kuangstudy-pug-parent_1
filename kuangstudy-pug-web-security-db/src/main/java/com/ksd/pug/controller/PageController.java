package com.ksd.pug.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/1 23:35
 */
@Controller
public class PageController {


    /**
     * 首页
     * @return
     */
    @RequestMapping(value = {"/index","/home","/"})
    public String index() {
        return "index";
    }

    /**
     * 登录
     * @return
     */
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
        return "cerror";
    }


    @GetMapping("session/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String sessionInvalid(){
        return "session已失效，请重新认证";
    }

}
