package com.example.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.model.BookHistory;
import com.example.model.BorrowedBook;
import com.example.model.User;
import com.example.repository.BookHistoryRepository;
import com.example.repository.BooksRepository;
import com.example.repository.BorrowedBookRepository;
import com.example.repository.UserRepository;

// このクラスは、本に関する操作を行うためのものです
@Service
public class BookService {

    // 以下の四つは、本やユーザー、借りた本、本の履歴といったデータを操作するためのツールです
    @Autowired
    private BooksRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private BookHistoryRepository bookHistoryRepository;

    // このメソッドは、指定されたユーザーが指定された本を借りるときに使います
    public void borrowBook(int bookId, int userId) {
        // ユーザーと本をデータから探し出します
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));

        // 本の状態を「借りられた」に変更して、データに保存します
        book.setStatus("borrowed");
        bookRepository.save(book);

        // 「借りた本」のデータを作って保存します
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setBook(book);
        borrowedBook.setUser(user);
        borrowedBook.setBorrowedDate(LocalDate.now());
        borrowedBookRepository.save(borrowedBook);

        // 「本の履歴」のデータを作って保存します
        BookHistory history = new BookHistory();
        history.setBook(book);
        history.setUser(user);
        history.setBorrowedDate(LocalDate.now());
        history.setAction("borrow"); // 操作を「借りる」に設定
        bookHistoryRepository.save(history);
    }

    // このメソッドは、指定された本が返されたときに使います
    public void returnBook(int bookId) {
        // 本と「借りた本」のデータを探し出します
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));
        BorrowedBook borrowedBook = borrowedBookRepository.findByBook(book).stream().findFirst().orElseThrow(() -> new IllegalArgumentException("No borrowed record found for book Id:" + bookId));

        // 本の状態を「利用可能」に変更して、データに保存します
        book.setStatus("available");
        bookRepository.save(book);

        // 「借りた本」のデータを削除します
        borrowedBookRepository.delete(borrowedBook);

        // 「本の履歴」のデータを作って保存します
        BookHistory history = new BookHistory();
        history.setBook(book);
        history.setUser(borrowedBook.getUser()); // 本は借りたユーザーが返しました
        history.setReturnedDate(LocalDate.now());
        history.setAction("return"); // 操作を「返す」に設定
        bookHistoryRepository.save(history);
    }

    // このメソッドは、指定されたユーザーが指定された本の状態を切り替えるとき（借りるか返すか）に使います
    public void toggleBookStatus(int bookId, int userId) {
        // 本をデータから探し出します
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));

        // 本の状態によって、「借りる」メソッドか「返す」メソッドを実行します
        if ("available".equals(book.getStatus())) {
            borrowBook(bookId, userId);
        } else if ("borrowed".equals(book.getStatus())) {
            returnBook(bookId);
        }
    }
}
