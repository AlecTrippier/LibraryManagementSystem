// この部分は、このコードが何処にあるのか（どのフォルダにあるのか）を示しています。"com.example.model"というのは、このコードがどこにあるかを示す場所のようなものです。
package com.example.model;

// このコードで使う機能をここで読み込んでいます。これによって、このコードの中で、それらの機能を自由に使えるようになります。
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

// @Entityは、このクラスがデータベースのテーブルに対応することを示しています。
@Entity
// @Tableは、このクラスがどのテーブルに対応するかを指定しています。ここでは、"books"というテーブルに対応していると宣言しています。
@Table(name="books")
// @Getterと@Setterは、このクラスの全てのフィールド（この下に書かれているid、titleなど）について、自動的に取得や設定のためのコードを作成することを指示します。これにより、別のコードからこれらの値を取得したり設定したりすることが可能になります。
@Getter
@Setter
public class Book {
    // @Idは、このフィールドがテーブルの主キー（ユニークなID）であることを示しています。
    @Id
    private int id;

    // 以下は、本の情報を保存するためのフィールド（変数）です。タイトル、著者、出版年、出版社、ジャンル、状態を保存します。
    private String title;
    private String author;
    private int publicationYear;
    private String publisher;
    private String genre;
    private String status;

    // @ManyToOneと@JoinColumnは、本が誰に借りられているかを示すためのフィールドを作成します。Userというクラスのオブジェクト（つまり、借りているユーザー）を保存します。この本が借りられていない場合は、このフィールドは空（null）になります。
    @ManyToOne
    @JoinColumn(name = "borrowed_user_id")
    private User borrowedUser;
}
