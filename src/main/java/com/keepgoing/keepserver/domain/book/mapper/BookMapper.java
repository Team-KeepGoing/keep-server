package com.keepgoing.keepserver.domain.book.mapper;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.dto.BookDTO;
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
                .state(entity.getState())
                .build();
    }

    public Book dtoToEntity(BookDTO dto) {
        return Book.builder()
                .bookName(dto.getBookName())
                .imageUrl(dto.getImageUrl())
                .state(BookState.AVAILABLE)
                .build();
    }

    public List<BookResponseDto> convertDevicesToDtos(List<Book> books) {
        return books.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}