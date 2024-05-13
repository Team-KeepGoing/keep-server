package com.keepgoing.keepserver.domain.book.presentation;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.global.response.CustomResponseEntity;
import com.keepgoing.keepserver.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookRepository bookRepository;
    private final BookService bookService;
    @PostMapping("/register")
    public CustomResponseEntity<String> bookRegistration(@RequestPart(value = "book") Book book,
                @RequestPart(value = "image") MultipartFile multipartFile){
        bookService.bookRegistration(book,multipartFile);
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

    @DeleteMapping("/del/{nfcCode}")
    public CustomResponseEntity<String> deleteBookByNfcCode(@PathVariable(value = "nfcCode") String nfcCode){
        return new CustomResponseEntity<String>(
                HttpStatus.OK,
                bookService.deleteBookByNfcCode(nfcCode)
        );
    }







}
