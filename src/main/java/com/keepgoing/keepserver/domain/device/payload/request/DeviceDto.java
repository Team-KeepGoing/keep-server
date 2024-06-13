package com.keepgoing.keepserver.domain.device.payload.request;

import com.keepgoing.keepserver.domain.device.enums.DeviceStatus;

import java.time.LocalDateTime;

public record DeviceDto (
        Long id,
        String deviceName,
        String imgUrl,
        LocalDateTime regDate,
        LocalDateTime rentDate,
        DeviceStatus status
) {
}
