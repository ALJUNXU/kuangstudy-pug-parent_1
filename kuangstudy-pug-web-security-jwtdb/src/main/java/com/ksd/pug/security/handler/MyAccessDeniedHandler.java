package com.ksd.pug.security.handler;

import com.pug.commons.utils.json.FastJsonUtil;
import com.pug.commons.utils.web.WebUtils;
import com.pug.resultex.domain.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 自定义授权失败处理器
 * Author: ryt
 * Version: 1.0
 * Create Date Time: 2021/12/24 11:20.
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    // 授权失败处理
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 处理异常
        String resultJson = FastJsonUtil.toJSON(R.error(601,"亲，您的权限不够,无权访问"));
        WebUtils.renderString(response,resultJson);
    }
}
