package com.pug.redis.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 9:55
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({PugRedisConfiguration.class})
public @interface EnablePugLimiter {
}
