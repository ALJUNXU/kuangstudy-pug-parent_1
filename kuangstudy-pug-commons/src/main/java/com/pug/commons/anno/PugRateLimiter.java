package com.pug.commons.anno;

import com.pug.commons.enums.LimiterType;

import java.lang.annotation.*;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/12/22 23:03
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PugRateLimiter {
    /**
     * 限流key
     */
    String key() default "rate_limiter:";

    /**
     * 限流类型
     */
    LimiterType limitType() default LimiterType.IP;

    /**
     * 限流次数
     * 每timeout限制请求的个数
     */
    int limit() default 10;

    /**
     * 限流时间,单位秒
     */
    int timeout() default 1;
}
