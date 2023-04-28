package com.pug.resultex.anno;

import com.pug.resultex.handler.GlobalExceptionControllerHandler;
import com.pug.resultex.handler.ResultResponseHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/3 21:41
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({GlobalExceptionControllerHandler.class, ResultResponseHandler.class})
public @interface EnableResultEx {
}
