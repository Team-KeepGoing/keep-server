package com.keepgoing.keepserver.domain.book.entity.dto;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public record BookResponseDto(
        Long id,
        String bookName,
        String imageUrl,
        Date registrationDate,
        LocalDateTime rentDate,
        BookState state
) {
}