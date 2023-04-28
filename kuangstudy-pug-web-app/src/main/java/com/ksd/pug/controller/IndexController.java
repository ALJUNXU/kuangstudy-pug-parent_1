package com.ksd.pug.controller;

import com.pug.commons.anno.PugRateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
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
public class IndexController {


    @GetMapping("/index")
    @PugRateLimiter(timeout =1,limit = 3)
    public String index() {
        return "Hello springboot!!!";
    }
}
