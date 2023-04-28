package com.ksd.pug.service.user;

import com.ksd.pug.pojo.SysLoginUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author yykk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDetail implements UserDetails {

    private SysLoginUser sysLoginUser;


    // 无需授权角色和用户
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return sysLoginUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysLoginUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}