package com.ksd.pug.controller;

import com.ksd.pug.pojo.SysLoginUser;
import com.ksd.pug.service.login.ILoginServcie;
import com.pug.resultex.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private ILoginServcie loginServcie;

    @PostMapping("/user/login")
    public R login(@RequestBody SysLoginUser user) {
        return loginServcie.login(user);
    }

}
