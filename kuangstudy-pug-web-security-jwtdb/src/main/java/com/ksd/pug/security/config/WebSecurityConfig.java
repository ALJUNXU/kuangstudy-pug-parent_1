package com.ksd.pug.security.config;

import com.ksd.pug.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * Created by yykk on 17/1/18.
 */


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private MyAuthenticationEntryPointHandler myAuthenticationEntryPointHandler;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyLogoutHandler myLogoutHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login", "/user/logout").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                //不通过Session获取SecurityContext
                .sessionManagement()
                //前后端分离采用JWT，禁用session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).disable();

        http.logout()
                // 退出登录的url, 默认为/logout
                .logoutUrl("/user/logout")
                .addLogoutHandler(myLogoutHandler)
                // 退出成功跳转URL，注意该URL不需要权限验证，所有加.permitAll
                //.logoutSuccessUrl("/logout/success").permitAll()
                //退出登录成功处理器
                .logoutSuccessHandler(customLogoutSuccessHandler)
                // 退出登录删除指定的cookie
                .deleteCookies("JSESSIONID")
                .permitAll();


        //关闭csrf 和 跨域
        http.cors().and().csrf().disable();


        // 将自定义认证失败处理器 和 自定义授权失败处理器 配置到Security中
        http.exceptionHandling()
                // 认证失败处理器
                .authenticationEntryPoint(myAuthenticationEntryPointHandler)
                // 授权失败处理器
                .accessDeniedHandler(myAccessDeniedHandler);

        http.addFilterBefore(jwtAuthenticationTokenFilter, LogoutFilter.class);
        // 将自定义的认证过滤器 放入到  UsernamePasswordAuthenticationFilter 过滤器 执行之前 进行执行！
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}