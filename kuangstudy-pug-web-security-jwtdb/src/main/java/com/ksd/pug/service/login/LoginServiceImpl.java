package com.ksd.pug.service.login;

import com.ksd.pug.pojo.SysLoginUser;
import com.ksd.pug.service.jwt.JwtUtil;
import com.ksd.pug.service.user.LoginUserDetail;
import com.pug.redis.config.PugRedisCacheTemplate;
import com.pug.resultex.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements ILoginServcie {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PugRedisCacheTemplate redisCacheTemplate;

    @Override
    public R login(SysLoginUser user) {
        // 根据账号和密码去登录
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        LoginUserDetail loginUserDetail = (LoginUserDetail) authenticate.getPrincipal();
        String userId = loginUserDetail.getSysLoginUser().getId() + "";
        // 生成 token
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCacheTemplate.setCacheObject("login:" + userId, loginUserDetail);
        //把token响应给前端
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        return R.success(map);
    }

    @Override
    public R logout(Authentication authentication) {
        LoginUserDetail loginUserDetail = (LoginUserDetail) authentication.getPrincipal();
        Long userid = loginUserDetail.getSysLoginUser().getId();
        redisCacheTemplate.deleteObject("login:"+userid);
        return R.success("退出成功");
    }
}
