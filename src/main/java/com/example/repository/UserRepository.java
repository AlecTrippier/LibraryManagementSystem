package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.User;

// UserRepositoryはデータベースへの操作を定義する場所（インターフェース）です。Userという種類のデータに対して、データベースからデータを取り出したり、データベースにデータを保存したり、データを更新したり、データを削除したりするための操作を定義します。
public interface UserRepository extends JpaRepository<User, Integer> {

    // "findByUsername"という操作は、与えられたユーザー名を持つUserを探し出して返します。例えば、"findByUsername("bob")"とすると、ユーザー名が"bob"のUserをデータベースから探し出して返します。この操作は、データベースに問い合わせを行い、その結果をUserとして返すことを意味します。
    User findByUsername(String username);
}
