package com.keepgoing.keepserver.domain.book.entity.dto;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookResponseDto(
        Long id,
        String bookName,
        String writer,
        String imageUrl,
        LocalDateTime registrationDate,
        LocalDateTime rentDate,
        BookState state
) {
}