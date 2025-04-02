package com.keepgoing.keepserver.domain.teacher.payload.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ItemResponseDto(
        Long id,
        String item,
        String serial_number,
        LocalDateTime acquisition_date,
        LocalDateTime price,
        String register_person,
        LocalDateTime usage_date,
        LocalDateTime memo
) {
}
