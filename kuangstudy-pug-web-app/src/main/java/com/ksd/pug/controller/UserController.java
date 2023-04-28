package com.ksd.pug.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksd.pug.pojo.SysLoginUser;
import com.ksd.pug.service.user.IUserService;
import com.pug.resultex.ex.OrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    private final IUserService userService;

    @GetMapping("/user/list")
    public String index() {
        if (true) {
            throw new OrderException(600, "订单有误!!!");
        }
        return "Hello springboot!!!";
    }


    @GetMapping("/saveuser")
    public SysLoginUser getuser(@RequestBody SysLoginUser SysLoginUser) {
        boolean b = userService.saveOrUpdate(SysLoginUser);
        return b ? SysLoginUser : null;
    }


    @GetMapping("/getuser")
    public SysLoginUser getuser() {
        return userService.getById(1L);
    }


    @GetMapping("/selectAll")
    public List<SysLoginUser> findAllUserByPageF(int pageNum, int pageSize) {
        Page<SysLoginUser> page = new Page(pageNum, pageSize);
        List<SysLoginUser> lists = userService.page(page, null).getRecords();
        return lists;
    }

    @GetMapping("/selectPage")
    public IPage<SysLoginUser> findAllUserByPageS(int pageNum, int pageSize) {
        Page<SysLoginUser> page = new Page(pageNum, pageSize);
        IPage<SysLoginUser> usersIPage = userService.page(page, null);
        return usersIPage;
    }
}
