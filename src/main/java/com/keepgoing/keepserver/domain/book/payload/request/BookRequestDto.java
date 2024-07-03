package com.keepgoing.keepserver.domain.book.payload.request;

import com.keepgoing.keepserver.domain.book.entity.enums.BookState;
import lombok.Builder;
import lombok.Getter;

@Builder
public record BookRequestDto (
        String name,
        String nfcCode,
        String imageUrl,
        BookState state
){
}
