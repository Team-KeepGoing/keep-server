package com.keepgoing.keepserver.domain.book.payload.response;

import com.keepgoing.keepserver.domain.book.domain.enums.BookState;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookResponseDto(
        long id,
        String bookName,
        String writer,
        String imageUrl,
        String nfcCode,
        LocalDateTime registrationDate,
        LocalDateTime rentDate,
        BookState state
) {
    @QueryProjection
    public BookResponseDto {
    }
}