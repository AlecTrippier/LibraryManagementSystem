package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.model.UserDto;
import com.example.repository.UserRepository;

// このクラスは、ユーザーに関する機能を提供します。ログインのためのユーザー検索や新規ユーザーの作成などができます。
@Service
public class UserService implements UserDetailsService {

    // UserRepositoryとPasswordEncoderを使うために、Autowiredで自動的に接続します。
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ユーザーネームをもとに、ユーザー情報を取得します。取得できない場合はエラーを返します。
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }

    //ユーザーネームをもとに、ユーザー情報を探します。取得できない場合はnullを返します。
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 新しいユーザーを作成して保存します。パスワードは暗号化して保存します。
    @Transactional
    public void save(UserDto userDto) {
        // UserDtoからUserへの変換
        User user = new User();
        user.setUsername(userDto.getUsername());
        // パスワードを暗号化してから保存
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        // データベースへの保存
        userRepository.save(user);
    }
}
