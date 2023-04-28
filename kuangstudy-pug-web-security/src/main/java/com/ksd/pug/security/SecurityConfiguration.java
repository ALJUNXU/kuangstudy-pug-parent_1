package com.ksd.pug.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 16:28
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public MyAccessDeniedHandler myAccessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PersistentTokenRepository getPersistentTokenRepository;

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.debug(true);
        webSecurity.ignoring().antMatchers("/index",
                "/js/**", "/css/**", "/fonts/**", "/images/**", "/img/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String encodePwd = passwordEncoder().encode("123456");
        // 在内存中配置指定两个用户
        auth.inMemoryAuthentication().withUser("admin")
                .roles("admin")
                .password(encodePwd)
                .and()
                .withUser("yykk")
                .password(encodePwd)
                .roles("user");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单提交
        http.formLogin()
                //自定义登录页面
                //.loginPage("/login.html")
                .loginPage("/toLogin")
                //当发现/login时认为是登录，必须和表单提交的地址一样。去执行UserServiceImpl
                .loginProcessingUrl("/login")
                //登录成功后跳转页面，POST请求
                .successForwardUrl("/toIndex")
                //登录失败后跳转页面，POST请求
                .failureForwardUrl("/toError")
                //设置请求账户和密码的参数名
                .passwordParameter("username")
                .passwordParameter("password")
                .and()
                .authorizeRequests()
                //不需要被认证
                //.antMatchers("/", "/index").access("hasRole('user')")
                //.antMatchers("/getuser/**").access("hasRole('admin')")
                //.antMatchers("/api/**").access("hasRole('admin') and hasRole('user')")
                .antMatchers("/", "/index").hasRole("user")
                .antMatchers("/getuser/**").hasRole("admin")
                .antMatchers("/api/**").hasAnyRole("user", "admin")
                .and()
                //403 异常处理
                .exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler())
                .and()
                .rememberMe()
                //失效时间，单位秒
                .tokenValiditySeconds(60)
                //登录逻辑交给哪个对象
                .userDetailsService(userDetailsService)
                // 持久层对象
                .tokenRepository(getPersistentTokenRepository)
                .and()
                .logout()
                .logoutUrl("/logout")
                //.logoutSuccessUrl("/login.html")
                .logoutSuccessUrl("/toLogin")
                //所有请求都必须被认证，必须登录后被访问
                //.anyRequest().authenticated()
                //关闭csrf防护
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
