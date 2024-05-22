package com.keepgoing.keepserver.domain.book.presentation;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.dto.response.CustomResponseEntity;
import com.keepgoing.keepserver.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    @PostMapping("/register")
    public BaseResponse bookRegistration(@RequestPart(value = "book") Book book,
                                         @RequestPart(value = "image") MultipartFile multipartFile) {
        return bookService.bookRegister(book,multipartFile);
    }

    @GetMapping("/all")
    public BaseResponse selectAllBook() {
        return bookService.selectAllBook();
    }

    @DeleteMapping("/del/{nfcCode}")
    public BaseResponse deleteBookByNfcCode(@PathVariable(value = "nfcCode") String nfcCode) {
        return bookService.deleteBook(nfcCode);
    }
}
