package com.keepgoing.keepserver.domain.device.payload.response;

import com.keepgoing.keepserver.domain.device.entity.enums.DeviceStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DeviceResponseDto (
        Long id,
        String deviceName,
        String imgUrl,
        LocalDateTime regDate,
        LocalDateTime rentDate,
        DeviceStatus status
) {
}
