package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Book;

// このコードは、"Book"という名前のものをデータベースに保存したり、取り出したりするための「作業指示」をまとめたもの（インターフェース）を作っています。
// "CrudRepository"という部分は、データベースにデータを作成したり、取り出したり、更新したり、削除したりするための基本的な作業指示をまとめたものを利用しています。
public interface BooksRepository extends CrudRepository<Book, Integer> {
    // ここには特に作業指示が書かれていませんが、"CrudRepository"から受け継いだ作業指示を使うことができます。
    // "Book"という部分は、この「作業指示」がどの種類のデータ（この場合は「Book」）に対して働くかを示しています。
    // "Integer"という部分は、「Book」の各項目を一意に識別するための「ID」が整数（Integer）であることを示しています。
}
