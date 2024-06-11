package com.keepgoing.keepserver.domain.book.mapper;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.dto.BookResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public static BookResponseDto entityToDto(Book entity) {
        return BookResponseDto.builder()
                .id(entity.getId())
                .bookName(entity.getBookName())
                .imageUrl(entity.getImageUrl())
                .state(entity.getState())
                .build();
    }
}