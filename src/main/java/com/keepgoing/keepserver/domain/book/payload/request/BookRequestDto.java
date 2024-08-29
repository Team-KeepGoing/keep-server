package com.keepgoing.keepserver.domain.book.payload.request;

import com.keepgoing.keepserver.domain.book.domain.entity.enums.BookState;
import lombok.Builder;

@Builder
public record BookRequestDto (
        String name,
        String nfcCode,
        String imageUrl,
        BookState state
){
}
