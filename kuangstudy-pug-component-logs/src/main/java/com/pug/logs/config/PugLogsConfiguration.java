package com.pug.logs.config;

import com.pug.logs.aop.PugLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/5/20 13:16
 */
@Configuration
public class PugLogsConfiguration {

    /**
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String, java.lang.Object>
     * @Author 徐柯
     * @Description 改写redistemplate序列化规则
     * @Date 13:20 2021/5/20
     * @Param [redisConnectionFactory]
     **/
    @Bean
    public PugLogAspect pugLogAspect() {
        return new PugLogAspect();
    }


}