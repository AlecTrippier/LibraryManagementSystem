package com.example.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.model.User;

// このクラスは、ユーザーの詳細情報を保持して、Spring Securityで利用します。
public class UserPrincipal implements UserDetails {

    private User user; // ユーザー情報

    // ユーザー情報を受け取ってセットします
    public UserPrincipal(User user) {
        this.user = user;
    }

    // このユーザーに与えられている権限（アクセスできる機能等）を返します。この例では、全てのユーザーに"USER"という権限を与えています
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    // ユーザーのパスワードを返します
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // ユーザーのユーザーネームを返します
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // アカウントが有効期限内であるかを返します。ここでは常にtrue（有効）を返します
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // アカウントがロックされていないかを返します。ここでは常にtrue（ロックされていない）を返します
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 資格情報（パスワードなど）が有効期限内であるかを返します。ここでは常にtrue（有効）を返します
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // アカウントが有効（使用可能）であるかを返します。ここでは常にtrue（有効）を返します
    @Override
    public boolean isEnabled() {
        return true;
    }
}
