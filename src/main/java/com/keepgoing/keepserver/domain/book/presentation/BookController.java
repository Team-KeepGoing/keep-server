package com.keepgoing.keepserver.domain.book.presentation;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.dto.BookRequestDto;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import com.keepgoing.keepserver.domain.user.payload.request.UserProfileDto;
import com.keepgoing.keepserver.domain.user.repository.user.UserRepository;
import com.keepgoing.keepserver.domain.user.service.user.UserService;
import com.keepgoing.keepserver.domain.user.service.user.UserServiceImpl;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.domain.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Tag(name = "책", description = " 관련 api 입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "책 등록", description = "책 등록 진행합니다.")
    @PostMapping("/register")
    public BaseResponse bookRegistration(@RequestPart(value = "book") Book book, @RequestPart(value = "image") MultipartFile multipartFile) {
        return bookService.bookRegister(book,multipartFile);
    }

    @Operation(summary = "전체 책 불러오기", description = "모든 책을 불러옵니다.")
    @GetMapping("/all")
    public BaseResponse selectAllBook() {
        return bookService.selectAllBook();
    }

    @Operation(summary = "책 삭제하기", description = "nfc코드를 통해 책을 삭제합니다")
    @DeleteMapping("/del/{nfcCode}")
    public BaseResponse deleteBookByNfcCode(@PathVariable(value = "nfcCode") String nfcCode,Authentication auth) {
        return bookService.deleteBook(nfcCode,auth);
    }

    @Operation(summary = "나의 책 불러오기", description = "Authentication을 통해 현재 대여중인 나의 책을 불러옵니다.")
    @GetMapping("/my")
    public BaseResponse selectMyBook(Authentication auth){
        return bookService.selectMyBook(auth);
    }

    @Operation(summary = "책 정보 수정하기", description = "nfc코드를 통해 책 정보를 수정합니다. 파라미터는 전체 코드가 아닌, 수정할 내용만 넘기셔도 됩니다.")
    @PatchMapping("/edit/{nfcCode}")
    public BaseResponse editBookByNfcCode(@PathVariable(value = "nfcCode") String nfcCode,
                                          @RequestBody BookRequestDto bookRequest) throws IOException {
        return bookService.editBook(nfcCode, bookRequest);
    }
}
