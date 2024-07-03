package com.keepgoing.keepserver.domain.book.payload.request;

import com.keepgoing.keepserver.domain.book.entity.enums.BookState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
