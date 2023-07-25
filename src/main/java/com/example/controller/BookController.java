// JavaとSpringを使って書かれたコードの一部です。"com.example.controller"という場所にあると宣言しています。
package com.example.controller;

// 必要なクラスや機能をJavaに知らせるためにインポートしています。
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.service.BookService;

// このクラスはウェブページの制御役（コントローラー）であることを示しています。
// "/api/books"というURLパスにアクセスがあった時、このクラスが動きます。
@Controller
@RequestMapping("/api/books")
public class BookController {// BookServiceという名前のサービス（特定の仕事を担当するクラス）を使うために準備しています。
	// @Autowiredを使うと、Springが自動的に必要なサービスを見つけてくれます。
	@Autowired
	private BookService bookService;

	// "/borrowBook"というURLにPOSTリクエストが来た時にこのメソッドが呼ばれます。
	// つまり、ユーザーが本を借りるときにはこの部分が動きます。
	@PostMapping("/borrowBook")
	public RedirectView borrowBook(@RequestParam("bookId") int bookId, @RequestParam("userId") int userId) {
	    // BookServiceを使って、具体的な本とユーザーを指定して本を借りる動作を行います。
	    bookService.borrowBook(bookId, userId);
	    // 本を借りた後は、その本の詳細ページに移動します。
	    return new RedirectView("/books/details/" + bookId);
	}

	// "/returnBook"というURLにPOSTリクエストが来た時にこのメソッドが呼ばれます。
	// ユーザーが本を返すときにはこの部分が動きます。
	@PostMapping("/returnBook")
	public RedirectView returnBook(@RequestParam("bookId") int bookId) {
	    // BookServiceを使って、具体的な本を指定して本を返す動作を行います。
	    bookService.returnBook(bookId);
	    // 本を返した後は、その本の詳細ページに移動します。
	    return new RedirectView("/books/details/" + bookId);
	}
	}