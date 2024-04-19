package com.keepgoing.keepserver.domain.book.controller;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.CustomResponseEntity;
import com.keepgoing.keepserver.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    @PostMapping("/register")
    public CustomResponseEntity<String> bookRegistration(@RequestBody Book book){
        bookService.bookRegistration(book);
        return new CustomResponseEntity<String>(
                HttpStatus.OK,
                "Book Adding Successful"
        );
    }

    @GetMapping("/allBook")
    public CustomResponseEntity<ArrayList<Book>> selectAllBook(){
        return new CustomResponseEntity<ArrayList<Book>>(
                HttpStatus.OK,
                (ArrayList<Book>) bookService.selectAllBook()
        );
    }

    @PostMapping("/deleteBook")
    public CustomResponseEntity<String> deleteBookByName(String name){
        bookService.deleteBook(name);
        return new CustomResponseEntity<String>(
                HttpStatus.OK,
                "delete Successful"
        );
    }





}
