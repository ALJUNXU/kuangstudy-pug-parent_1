package com.ksd.pug.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ksd.orm.pug.mapper.SysLoginUserMapper;
import com.ksd.orm.pug.mapper.SysPermissionMapper;
import com.ksd.pug.pojo.SysLoginUser;
import com.ksd.pug.pojo.SysPermission;
import com.ksd.pug.pojo.SysRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 23:35
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoginUserDetailService extends ServiceImpl<SysLoginUserMapper, SysLoginUser> implements UserDetailsService, ILoginUserService {

    private final SysPermissionMapper sysPermissionMapper;

    public UserDetails loadUserByUsername(String username) {
        // 1: 根据username查询用户信息
        LambdaQueryWrapper<SysLoginUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysLoginUser::getUsername, username);
        SysLoginUser user = this.getOne(userLambdaQueryWrapper);
        if (user != null) {
            // 2：根据用户查询对应的角色
            List<SysRole> sysRoles = this.baseMapper.findSysRoleByUserId(user.getId());
            List<SysPermission> permissions = sysPermissionMapper.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>(sysRoles.size() + permissions.size());
            /*在Spring Security中，角色和权限统称为GrantAuthority，角色和权限都交给GrantAuthenty管理，而区分角色和权限的方式，就是在角色名称前加前缀ROLE_以表示角色*/
            // 将角色信息转换为SimpleGrantedAuthority对象类型
            List<SimpleGrantedAuthority> roleAuthorities = sysRoles.stream()
                    //给角色名称增加前缀ROLE_
//                    .map(role -> "ROLE_" + role.getName())
                    .map(SysRole::getName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            // 将授权许可信息转换为SimpleGrantedAuthority对象类型
            List<SimpleGrantedAuthority> permissionAuthorities = permissions.stream()
                    .map(SysPermission::getName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            /*将角色和授权许可合并到grantedAuthorities列表*/
            grantedAuthorities.addAll(roleAuthorities);
            grantedAuthorities.addAll(permissionAuthorities);
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}
