package com.keepgoing.keepserver.domain.book.mapper;

import com.keepgoing.keepserver.domain.book.entity.enums.BookState;
import com.keepgoing.keepserver.domain.book.entity.Book;
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

    public Book dtoToEntity(BookDto dto) {
        return Book.builder()
                .bookName(dto.getBookName())
                .imageUrl(dto.getImageUrl())
                .registrationDate(LocalDateTime.now())
                .rentDate(dto.getRentDate())
                .writer(dto.getWriter())
                .state(BookState.AVAILABLE)
                .nfcCode(dto.getNfcCode())
                .build();
    }

    public List<BookResponseDto> convertBooksToDtos(List<Book> books) {
        return books.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}