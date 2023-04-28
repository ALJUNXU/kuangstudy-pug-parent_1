package com.ksd.pug.security.config;

import com.ksd.pug.security.handler.CustomSessionInformationExpiredStrategy;
import com.ksd.pug.security.handler.MyAccessDeniedHandler;
import com.ksd.pug.security.handler.MyInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yykk on 17/1/18.
 */


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService loginUserDetailService;
    @Autowired
    private CustomSessionInformationExpiredStrategy customSessionInformationExpiredStrategy;
    @Autowired
    private MyInvalidSessionStrategy myInvalidSessionStrategy;

    @Bean
    public MyAccessDeniedHandler myAccessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }

    @Resource
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String encodePwd = passwordEncoder().encode("123456");
        // 在内存中配置指定两个用户
        auth.inMemoryAuthentication().withUser("super")
                .roles("Admin")
                .password(encodePwd)
                .and()
                .withUser("yykk")
                .password(encodePwd)
                .roles("User");
        // 使用自实现的loginUserDetailService来加载数据库中的用户和权限信息
        auth.userDetailsService(loginUserDetailService) //user Details Service验证
                // 设置加密的方式，这里要和注册，创建用户时的密码要一致
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement()
                .invalidSessionStrategy(myInvalidSessionStrategy)
                //.invalidSessionUrl("/session/invalid")
                // 设置同一个用户只能有一个登陆session
                .maximumSessions(1)
                // 设置为true，即禁止后面其它人的登录
                //.maxSessionsPreventsLogin(true)
                // 其他地方登录session失效处理URL
                // .expiredUrl("/session/expired");
                .expiredSessionStrategy(customSessionInformationExpiredStrategy);
        http
                //开启登录配置
                .authorizeRequests()
                //设置不需要认证的路径，一律允许通过，这种方式会走Spring Security的过滤器链，当遇到这些访问路径时直接允许通过
                .antMatchers("/session/invalid","/session/expired","/index", "/home", "/toLogin", "/login", "/toError", "/logout", "/cerror.html", "/login.html").permitAll()
                //设置需要角色的路径，表示访问 /user/view 这个接口，需要具备 Admin 这个角色
                //.antMatchers("/getuser/**").hasRole("Admin")
                //.antMatchers("/user/view/**").hasRole("User")
                //设置需要权限许可的路径，表示访问 /user/list 这个接口，需要具有“user:list”权限许可
                //.antMatchers("/user/list").hasAuthority("home")
                //设置其他路径都需要登录后才可访问
                .anyRequest().authenticated()
                //开启一个新的设置项
                .and()
                //设置登录表单
                .formLogin()
                //定义登录页面，未登录时，会自动跳转到该页面
                .loginPage("/toLogin")
                //登录处理接口
                .loginProcessingUrl("/login")
                //定义登录时，用户名的 key，默认为 username
                .usernameParameter("username")
                //定义登录时，用户密码的 key，默认为 password
                .passwordParameter("password")
                //登录成功的处理器
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        out.write("success");
//                        out.flush();
                        resp.sendRedirect("/toIndex");
                    }
                })
                //登录失败后的处理器
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        out.write("fail");
//                        out.flush();
                        resp.sendRedirect("/toError");
                    }
                })
                //和表单登录相关的接口直接允许通过
                .permitAll()
                //开启一个新的设置项
                .and()
                //设置登出（注销登录）
                .logout()
                //设置登出路径
                .logoutUrl("/logout")
                // 退出成功跳转URL，注意该URL不需要权限验证，所有加.permitAll
                //.logoutSuccessUrl("/logout/success").permitAll()
                //设置登出成功后的处理器
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("text/html;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write("<script>alert(\"退出成功!!\");window.location.href = '/toLogin';</script>");
                        out.flush();
                        out.close();
                    }
                })
                // 退出登录删除指定的cookie
                .deleteCookies("JSESSIONID")
                //和登出相关的接口允许通过
                .permitAll()
                .and()
                //403 异常处理
                .exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler())
                .and()
                .rememberMe()
                //失效时间，单位秒
                .tokenValiditySeconds(60)
                .userDetailsService(loginUserDetailService)
                // 持久层对象
                .tokenRepository(persistentTokenRepository())
                .and()
                //启用Http Basic认证
                .httpBasic()
                .and()
                .cors() // 允许跨域
                .and()
                .csrf().disable();
    }

    @Bean
    public HttpSessionEventPublisher httpSessoinEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置不需要认证的路径，这种方式不走Spring Security过滤器链，推荐使用这种方式
        web.ignoring().antMatchers("/verifycode");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}