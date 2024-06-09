package com.keepgoing.keepserver.domain.device.payload.response;

import com.keepgoing.keepserver.domain.device.entity.DeviceStatus;
import lombok.Builder;

@Builder
public record DeviceResponseDto (
        Long id,
        String deviceName,
        String imgUrl,
        DeviceStatus status
) {
}
