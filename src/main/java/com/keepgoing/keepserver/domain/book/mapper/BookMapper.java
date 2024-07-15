package com.keepgoing.keepserver.domain.book.mapper;

import com.keepgoing.keepserver.domain.book.domain.entity.Book;
import com.keepgoing.keepserver.domain.book.domain.entity.enums.BookState;
import com.keepgoing.keepserver.domain.book.payload.request.BookDto;
import com.keepgoing.keepserver.domain.book.payload.response.BookResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public BookResponseDto entityToDto(Book entity) {
        return BookResponseDto.builder()
                .id(entity.getId())
                .bookName(entity.getBookName())
                .imageUrl(entity.getImageUrl())
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

    public List<BookResponseDto> convertBooksToDtos(List<Book> books) {
        return books.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}