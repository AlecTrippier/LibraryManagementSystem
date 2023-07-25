package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Book;
import com.example.model.BookHistory;
import com.example.model.User;

// このコードはデータベースのBookHistoryテーブルとのやりとりを管理するインターフェースを定義しています。
// CrudRepositoryを拡張していますので、データベースに対する基本的な操作（作成、読み込み、更新、削除など）をこのインターフェースを通して行うことができます。
public interface BookHistoryRepository extends CrudRepository<BookHistory, Integer> {

    // findByBookAndUserというメソッドは、特定の本(Book)とユーザー(User)による本の履歴(BookHistory)のリストをデータベースから探し出すために使います。
    // このメソッド名は特殊で、Spring Data JPAが自動的にこのメソッドの実装を提供します。
    // BookとUserオブジェクトを引数に渡すと、それにマッチする本の履歴のリストを返してくれます。
    List<BookHistory> findByBookAndUser(Book book, User user);
}
