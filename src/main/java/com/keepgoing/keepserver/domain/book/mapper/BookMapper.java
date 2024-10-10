package com.keepgoing.keepserver.domain.book.mapper;

import com.keepgoing.keepserver.domain.book.domain.entity.Book;
import com.keepgoing.keepserver.domain.book.domain.enums.BookState;
import com.keepgoing.keepserver.domain.book.payload.request.BookDto;
import com.keepgoing.keepserver.domain.book.payload.response.BookResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class BookMapper {
    public BookResponseDto entityToDto(Book entity) {
        return BookResponseDto.builder()
                .id(entity.getId())
                .bookName(entity.getBookName())
                .registrationDate(entity.getRegistrationDate())
                .writer(entity.getWriter())
                .imageUrl(entity.getImageUrl())
                .nfcCode(entity.getNfcCode())
                .rentDate(entity.getRentDate())
                .state(entity.getState())
                .build();
    }

    public Book dtoToEntity(BookDto dto, String nfcCode) {
        return Book.builder()
                .bookName(dto.bookName())
                .imageUrl(dto.imageUrl())
                .registrationDate(LocalDateTime.now())
                .rentDate(dto.rentDate())
                .writer(dto.writer())
                .state(BookState.AVAILABLE)
                .nfcCode(nfcCode)
                .build();
    }
}