// この部分は、このコードが何処にあるのか（どのフォルダにあるのか）を示しています。"com.example.model"というのは、このコードがどこにあるかを示す場所のようなものです。
package com.example.model;

// このコードで使う機能をここで読み込んでいます。これによって、このコードの中で、それらの機能を自由に使えるようになります。
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

// @Entityは、このクラスがデータベースのテーブルに対応することを示しています。
@Entity
// @Table(name = "Users")は、このクラスがどのテーブルに対応するかを示しています。この場合、"Users"という名前のテーブルに対応します。
@Table(name = "Users")
public class User {
    // 以下のフィールド（id、usernameなど）は、このユーザークラスの一部を形成します。

    // @Idは、このフィールドがテーブルの主キー（ユニークなID）であることを示しています。
    // @GeneratedValue(strategy = GenerationType.IDENTITY)は、このIDが自動的に生成されることを示しています。
    // @Column(name = "id")は、データベーステーブルでの列名が"id"であることを示しています。
    // @Getterと@Setterは、このフィールド（id）を取得したり（get）、設定したり（set）するための方法を自動的に作り出すことを示しています。
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // @Column(name = "username", nullable = false, unique = true)は、データベーステーブルでの列名が"username"であり、この値が必ず存在し、かつユニーク（他のユーザーと同じ名前がない）であることを示しています。
    // @Getterと@Setterは、このフィールド（username）を取得したり（get）、設定したり（set）するための方法を自動的に作り出すことを示しています。
    @Getter
    @Setter
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    // @Column(name = "password", nullable = false)は、データベーステーブルでの列名が"password"であり、この値が必ず存在することを示しています。
    // @Getterと@Setterは、このフィールド（password）を取得したり（get）、設定したり（set）するための方法を自動的に作り出すことを示しています。
    @Getter
    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    // @Column(name = "email", nullable = false, unique = true)は、データベーステーブルでの列名が"email"であり、この値が必ず存在し、かつユニーク（他のユーザーと同じメールアドレスがない）であることを示しています。
    // @Getterと@Setterは、このフィールド（email）を取得したり（get）、設定したり（set）するための方法を自動的に作り出すことを示しています。
    @Getter
    @Setter
    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
