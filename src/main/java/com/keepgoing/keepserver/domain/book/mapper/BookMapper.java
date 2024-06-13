package com.keepgoing.keepserver.domain.book.mapper;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.dto.BookDto;
import com.keepgoing.keepserver.domain.book.entity.dto.BookResponseDto;
import org.springframework.stereotype.Component;

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

    public static Book dtoToEntity(BookDto dto) {
        return Book.builder()
                .bookName(dto.getBookName())
                .imageUrl(dto.getImageUrl())
                .rentDate(dto.getRentDate())
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