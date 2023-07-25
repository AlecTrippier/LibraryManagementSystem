package com.example.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
    
    // 「@Getter」と「@Setter」は、このフィールドのための「get」メソッド（値を取得する）と「set」メソッド（値を設定する）を自動的に作成します。
    // 「@NotEmpty」は、このフィールドが空でないことを保証します。つまり、ユーザ名は必ず何かしらの値を持つことが必要です。
    @Getter
    @Setter
    @NotEmpty
    private String username;

    // 同様に、「@Getter」と「@Setter」はパスワードのための「get」メソッドと「set」メソッドを作ります。「@NotEmpty」は、パスワードが必ず存在することを保証します。
    @Getter
    @Setter
    @NotEmpty
    private String password;

    // メールアドレスのための「get」メソッドと「set」メソッドを作り、メールアドレスが必ず存在することを保証します。
    @Getter
    @Setter
    @NotEmpty
    private String email;
}
