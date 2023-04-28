package com.ksd.pug.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ksd.orm.pug.mapper.SysLoginUserMapper;
import com.ksd.orm.pug.mapper.SysPermissionMapper;
import com.ksd.pug.pojo.SysLoginUser;
import com.pug.commons.utils.fn.asserts.Vsserts;
import com.pug.resultex.ex.BussinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 23:35
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoginUserDetailService extends ServiceImpl<SysLoginUserMapper, SysLoginUser> implements UserDetailsService, ILoginUserService {

    private final SysPermissionMapper sysPermissionMapper;

    public UserDetails loadUserByUsername(String username) {
        // 1: 根据username查询用户信息
        LambdaQueryWrapper<SysLoginUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysLoginUser::getUsername, username);
        SysLoginUser user = this.getOne(userLambdaQueryWrapper);
        Vsserts.isNullEx(user, new BussinessException(600, "用户名或密码错误"));
        return new LoginUserDetail(user);
    }
}
