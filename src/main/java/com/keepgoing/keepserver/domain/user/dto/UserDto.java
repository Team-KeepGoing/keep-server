package com.keepgoing.keepserver.domain.user.dto;

import com.keepgoing.keepserver.domain.book.payload.response.BookResponseDto;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserDto(
        Long id,
        String email,
        String studentId,
        String name,
        boolean teacher,
        Set<DeviceResponseDto> borrowedDevices,
        Set<BookResponseDto> borrowedBooks
) {
    @QueryProjection
    public UserDto {}
}
