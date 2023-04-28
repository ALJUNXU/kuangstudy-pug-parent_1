package com.ksd.pug.security.handler;

import com.ksd.pug.service.jwt.JwtUtil;
import com.ksd.pug.service.user.LoginUserDetail;
import com.pug.commons.utils.fn.asserts.Vsserts;
import com.pug.redis.config.PugRedisCacheTemplate;
import com.pug.resultex.ex.BussinessException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 自定义认证的过滤器
 * Author: ryt
 * Version: 1.0
 * Create Date Time: 2021/12/22 15:12.
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private PugRedisCacheTemplate redisCacheTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1、获取token，解析token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 放行,进其他过滤器
            filterChain.doFilter(request, response);
            // 如果不return，后面响应完成后会回来进入下面的代码
            return;
        }

        String userId = null;
        try {
            // 2、根据解析的token拿到userid
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new BussinessException(602,"token非法");
        }
        // 3、在redis中根据userid获取对应的用户的信息
        String rediskey = "login:" + userId;
        LoginUserDetail loginUser = (LoginUserDetail)redisCacheTemplate.getCacheObject(rediskey);
        // 有可能redis不存在用户的信息
        if (Vsserts.isNull(loginUser)) {
            throw new BussinessException(602,"用户未登录，请重新登录");
        }
        // 4、再将用户信息存入SecurityContextHolder，用户后面过滤器的放行
        // 获取权限信息封装到Authentication对象中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 5、过滤器链放行！
        filterChain.doFilter(request, response);
    }

}
