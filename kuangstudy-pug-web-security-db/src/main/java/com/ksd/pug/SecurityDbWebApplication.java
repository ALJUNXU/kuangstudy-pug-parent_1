package com.ksd.pug;

import com.ksd.orm.pug.anno.EnableMpMybatis;
import com.pug.logs.config.EnablePugLogs;
import com.pug.redis.config.EnablePugLimiter;
import com.pug.resultex.anno.EnableResultEx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/1 23:34
 */
@SpringBootApplication
@EnablePugLogs
@EnablePugLimiter
@EnableMpMybatis
@EnableResultEx
public class SecurityDbWebApplication {

    public static void main(String[] args) {
        // 这个是启动类 2.0修改
        SpringApplication.run(SecurityDbWebApplication.class, args);
    }
}
