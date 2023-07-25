// この部分は、このコードが何処にあるのか（どのフォルダにあるのか）を示しています。"com.example.model"というのは、このコードがどこにあるかを示す場所のようなものです。
package com.example.model;

// このコードで使う機能をここで読み込んでいます。これによって、このコードの中で、それらの機能を自由に使えるようになります。
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

// @Entityは、このクラスがデータベースのテーブルに対応することを示しています。
@Entity
// @Getterと@Setterは、このクラスの全てのフィールド（この下に書かれているid、bookなど）について、自動的に取得や設定のためのコードを作成することを指示します。これにより、別のコードからこれらの値を取得したり設定したりすることが可能になります。
@Getter
@Setter
public class BookHistory {
    // @Idは、このフィールドがテーブルの主キー（ユニークなID）であることを示しています。
    // @GeneratedValueは、このIDが自動的に生成されることを示しています。
    @Id
    @GeneratedValue
    private int id;

    // この履歴エントリがどの本に関連するのかを保存するためのフィールドです。
    @ManyToOne
    private Book book;

    // この履歴エントリがどのユーザーに関連するのかを保存するためのフィールドです。
    @ManyToOne
    private User user;

    // 本が借りられた日と返却された日を保存するためのフィールドです。
    private LocalDate borrowedDate;
    private LocalDate returnedDate;

    // 行動（貸出か返却）を保存するためのフィールドです。
    private String action;

    // この下には、フィールドの値を取得したり設定したりするためのコードが入ります（getters and setters）。
}
