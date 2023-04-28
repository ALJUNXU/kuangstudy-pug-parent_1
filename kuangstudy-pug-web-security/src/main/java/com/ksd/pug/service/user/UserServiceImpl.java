package com.ksd.pug.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ksd.orm.pug.mapper.SysLoginUserMapper;
import com.ksd.pug.pojo.SysLoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 13:20
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<SysLoginUserMapper, SysLoginUser> implements IUserService{
}
