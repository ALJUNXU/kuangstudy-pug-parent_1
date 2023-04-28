package com.ksd.pug.security.handler;

import com.ksd.pug.service.login.ILoginServcie;
import com.pug.commons.utils.json.FastJsonUtil;
import com.pug.commons.utils.web.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private ILoginServcie loginServcie;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        WebUtils.renderString(response, FastJsonUtil.toJSON(loginServcie.logout(authentication)));
    }
}