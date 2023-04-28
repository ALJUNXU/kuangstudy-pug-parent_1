package com.ksd.pug.security.handler;

import com.pug.commons.utils.json.FastJsonUtil;
import com.pug.commons.utils.web.WebUtils;
import com.pug.resultex.domain.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 自定义认证异常处理器
 * Author: ryt
 * Version: 1.0
 * Create Date Time: 2021/12/24 11:14.
 */
@Component
public class MyAuthenticationEntryPointHandler implements AuthenticationEntryPoint {


    // 认证失败处理
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 处理异常
        String resultJson = FastJsonUtil.toJSON(R.error(601,"亲，用户认证失败，请重新登录"));
        WebUtils.renderString(response,resultJson);
    }

}

