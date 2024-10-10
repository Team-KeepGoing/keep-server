package com.keepgoing.keepserver.domain.device.payload.response;

import com.keepgoing.keepserver.domain.device.domain.enums.DeviceStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DeviceResponseDto(
        long id,
        String deviceName,
        String imgUrl,
        String borrower,
        String lastBorrowerMail,
        LocalDateTime regDate,
        LocalDateTime rentDate,
        DeviceStatus status
) {
    @QueryProjection
    public DeviceResponseDto {
    }
}
