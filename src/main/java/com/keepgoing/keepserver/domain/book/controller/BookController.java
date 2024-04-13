package com.keepgoing.keepserver.domain.book.controller;

import com.keepgoing.keepserver.domain.book.domain.Book;
import com.keepgoing.keepserver.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    @GetMapping("/register")
    public ResponseEntity<String> bookRegistration(@RequestBody Book book){
        bookService.bookRegistration(book);
        return new ResponseEntity<String>(HttpStatus.OK);

    }

}
