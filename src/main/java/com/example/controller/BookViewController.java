// この部分で、このファイルが何処にあるかを宣言しています。"com.example.controller"は、このファイルがどこにあるかを示すパスのようなものです。
package com.example.controller;

// このコードで使われるクラスやパッケージをここでインポートしています。これにより、このコード内でそれらの機能を自由に使用することができます。
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Book;
import com.example.model.BorrowedBook;
import com.example.repository.BooksRepository;
import com.example.repository.BorrowedBookRepository;
import com.example.repository.UserRepository;

// @Controllerアノテーションは、このクラスがウェブアプリケーションの一部であることをSpringに伝えます。このクラスは、"/books"のパスにアクセスがあったときに動作します。
@Controller
@RequestMapping("/books")
public class BookViewController {
    // データを取得するためのリポジトリをここで宣言します。
    private final BooksRepository booksRepository;
    private final UserRepository userRepository;

    // このリポジトリは@Autowiredを使って自動的に設定されます。つまり、Springがこのリポジトリの実装を探し、自動的に関連付けます。
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    // BookViewControllerの新しいインスタンスを作成するとき、SpringはBooksRepositoryとUserRepositoryを引数として提供します。それらはそれぞれbooksRepositoryとuserRepositoryフィールドに保存されます。
    @Autowired
    public BookViewController(BooksRepository booksRepository, UserRepository userRepository) {
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;
    }

    // "/details/{bookId}"という
// URLにGETリクエストが来たとき、このメソッドが呼び出されます。この場合、{bookId}は本のIDを表します。
@GetMapping("details/{bookId}")
public String getBookDetails(@PathVariable Integer bookId, Model model) {
// 特定のIDを持つ本を見つけます。見つからなかった場合は、エラーメッセージを表示します。
Book book = booksRepository.findById(bookId)
.orElseThrow(() -> new ResourceNotFoundException("指定されたIDの本が見つかりません: " + bookId));  // 見つかった本をmodel（画面に表示するためのデータの入れ物）に追加します。
model.addAttribute("book", book);

// もし本が借りられている場合、借りているユーザーの情報を取得します。
if ("borrowed".equals(book.getStatus())) {
    BorrowedBook borrowedBook = borrowedBookRepository.findByBook(book).stream().findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("This book is not currently borrowed: " + bookId));
    model.addAttribute("borrowedUser", borrowedBook.getUser());
}

// 現在ログインしているユーザーの情報を取得します。
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
UserDetails userDetails = (UserDetails) auth.getPrincipal();
com.example.model.User user = userRepository.findByUsername(userDetails.getUsername());

// ログインユーザーが見つからなかった場合、エラーメッセージを表示します。
if (user == null) {
    throw new ResourceNotFoundException("ログインユーザーが見つかりません: " + userDetails.getUsername());
}

// ログインユーザーのIDをmodelに追加します。
model.addAttribute("currentUserId", user.getId());

// 最後に、"bookDetails"という名前の画面（ビュー）を表示します。このビューの内容はSpringによって自動的に生成されます。
return "bookDetails";
}
}

