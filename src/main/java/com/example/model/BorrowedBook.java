// この部分は、このコードが何処にあるのか（どのフォルダにあるのか）を示しています。"com.example.model"というのは、このコードがどこにあるかを示す場所のようなものです。
package com.example.model;

// このコードで使う機能をここで読み込んでいます。これによって、このコードの中で、それらの機能を自由に使えるようになります。
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

// @Entityは、このクラスがデータベースのテーブルに対応することを示しています。
@Entity
// @Table(name="borrowed_books")は、このクラスがどのテーブルに対応するかを示しています。この場合、"borrowed_books"という名前のテーブルに対応します。
@Table(name="borrowed_books")
// @Getterと@Setterは、このクラスの全てのフィールド（この下に書かれているid、bookなど）について、自動的に取得や設定のためのコードを作成することを指示します。これにより、別のコードからこれらの値を取得したり設定したりすることが可能になります。
@Getter
@Setter
public class BorrowedBook {
    // @Idは、このフィールドがテーブルの主キー（ユニークなID）であることを示しています。
    // @GeneratedValue(strategy = GenerationType.AUTO)は、このIDが自動的に生成されることを示しています。
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // この借りた本がどの本に関連するのかを保存するためのフィールドです。@JoinColumn(name="book_Id", nullable=false)は、この値が必ず存在すること、そしてデータベーステーブルでの列名が"book_Id"であることを示しています。
    @ManyToOne
    @JoinColumn(name="book_Id", nullable=false)
    private Book book;

    // この借りた本がどのユーザーに関連するのかを保存するためのフィールドです。@JoinColumn(name="user_Id", nullable=false)は、この値が必ず存在すること、そしてデータベーステーブルでの列名が"user_Id"であることを示しています。
    @ManyToOne
    @JoinColumn(name="user_Id", nullable=false)
    private User user;

    // 本が借りられた日と返却された日を保存するためのフィールドです。
    private LocalDate borrowedDate;
    private LocalDate returnDate;
}
