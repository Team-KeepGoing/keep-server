package com.keepgoing.keepserver.domain.damage.payload.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DamageResponseDto(
        Long idx,
        String code,
        String issueType,
        String description,
        LocalDateTime reportDate
) {
}
