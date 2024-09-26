package com.keepgoing.keepserver.domain.book.payload.request;

import com.keepgoing.keepserver.domain.book.domain.enums.BookState;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookDto (
        Long id,
        String bookName,
        String writer,
        String imageUrl,
        String nfcCode,
        LocalDateTime registrationDate,
        LocalDateTime rentDate,
        BookState state
) {
}
