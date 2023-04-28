package com.ksd.pug.controller;

import com.ksd.pug.pojo.SysLoginUser;
import com.ksd.pug.service.user.ILoginUserService;
import com.pug.commons.anno.PugLog;
import com.pug.commons.anno.PugRateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/1 23:35
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final ILoginUserService userService;

    @GetMapping("/getuser/{id}")
    @PugRateLimiter(limit = 3, timeout = 1)
    public SysLoginUser getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }


    @GetMapping("/user/view/{id}")
    @PugRateLimiter(limit = 3, timeout = 1)
    public SysLoginUser userview(@PathVariable("id") Long id) {
        return userService.getById(id);
    }



    @GetMapping("/user/list")
    @PugLog
    @PugRateLimiter(limit = 3, timeout = 1)
    public String userlist() {
        return "list";
    }

}
