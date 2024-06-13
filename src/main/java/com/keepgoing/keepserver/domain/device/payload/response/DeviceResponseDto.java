package com.keepgoing.keepserver.domain.device.payload.response;

import com.keepgoing.keepserver.domain.device.enums.DeviceStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DeviceResponseDto (
        Long id,
        String deviceName,
        String imgUrl,
        LocalDateTime rentDate,
        DeviceStatus status
) {
}
