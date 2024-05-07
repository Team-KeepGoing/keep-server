package com.keepgoing.keepserver.domain.book.presentation;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.response.CustomResponseEntity;
import com.keepgoing.keepserver.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    @PostMapping("/register")
    public CustomResponseEntity<String> bookRegistration(@RequestBody Book book) {
        bookService.bookRegistration(book);
        return new CustomResponseEntity<String>(
                HttpStatus.OK,
                "BookAdding Successful"
        );
    }


    @GetMapping("/all")
    public CustomResponseEntity<ArrayList<Book>> selectAllBook(){
        return new CustomResponseEntity<ArrayList<Book>>(
                HttpStatus.OK,
                (ArrayList<Book>) bookService.selectAllBook()
        );
    }

    @PostMapping("/del")
    public CustomResponseEntity<String> deleteBookByNfcCode(@RequestBody String nfcCode){
        bookService.deleteBookByNfcCode(nfcCode);
        return new CustomResponseEntity<String>(
                HttpStatus.OK,
                "delete Successful"
        );
    }

    @PostMapping("/edit")
    public CustomResponseEntity<String> editBookByNfcCode(@RequestBody String nfcCode){
        bookService.editBookByNfcCode(nfcCode);
        return new CustomResponseEntity<String>(
                HttpStatus.OK,
                "edit Successful"
        );
    }






}
