package com.ksd.pug.service.login;

import com.ksd.pug.pojo.SysLoginUser;
import com.pug.resultex.domain.R;
import org.springframework.security.core.Authentication;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/3 15:59
 */
public interface ILoginServcie {

    R login(SysLoginUser user);

    R logout(Authentication authentication);
}
