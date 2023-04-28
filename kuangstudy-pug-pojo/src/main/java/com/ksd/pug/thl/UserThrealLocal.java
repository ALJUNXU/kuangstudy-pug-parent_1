package com.ksd.pug.thl;

import com.ksd.pug.pojo.SysLoginUser;

/**
 * @author 飞哥yykk
 * 更多学习关注飞哥B站
 * 地址是：https://space.bilibili.com/490711252
 * @title: UserThrealLocal
 * @projectName ksd-user-course-center
 * @description: TODO
 * @date 2021/9/2721:51
 */
public class UserThrealLocal {

    // 本地线程缓存: ThreadLocal 底层就是 Map
    private static ThreadLocal<SysLoginUser> userThreadLocal = new ThreadLocal<SysLoginUser>();

    public static void put(SysLoginUser user) {
        userThreadLocal.set(user);
    }

    public static SysLoginUser get() {
        return userThreadLocal.get();
    }

    public static void remove() {
        userThreadLocal.remove();
    }

}
