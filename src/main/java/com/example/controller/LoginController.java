package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.Book;
import com.example.repository.BooksRepository;

@Controller
public class LoginController {

    // BookRepositoryのインスタンスを自動的に注入します
    @Autowired
    private BooksRepository repository;

    // "/login"へのGETリクエストを処理します。loginビューを表示します
    @GetMapping("/login")
    public String login() {
        return "login";  
    }

    // "/"へのGETリクエストを処理します。認証済みであればindexページへリダイレクトし、
    // 認証されていなければloginページへリダイレクトします
    @GetMapping("/")
    public String redirectToIndex() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/index";  
        }
        return "redirect:/login";
    }

    // "/index"へのGETリクエストを処理します。すべてのBookのリストをデータベースから取得し、
    // それらをbooksという名前でモデルに追加し、indexビューを表示します
    @GetMapping("/index")
    public String index(Model model) {
        List<Book> books = new ArrayList<>();
        for(Book book : repository.findAll()) {
            books.add(book);
        }
        model.addAttribute("books", books);
        return "index";
    }

}
