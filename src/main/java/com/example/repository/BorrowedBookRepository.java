package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Book;
import com.example.model.BorrowedBook;

// このコードは、「BorrowedBook」（借りた本）に関するデータをデータベースから取得したり保存したりするための「仕事の手順」を定義しています。
public interface BorrowedBookRepository extends CrudRepository<BorrowedBook, Long> {
    // この部分は、特定の本を指定して、それが誰によって借りられたかのリストを取得するための「仕事の手順」を定義しています。
    List<BorrowedBook> findByBook(Book book);
}
