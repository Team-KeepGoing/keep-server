package com.keepgoing.keepserver.domain.device.payload.request;

import com.keepgoing.keepserver.domain.device.entity.DeviceStatus;
import lombok.Builder;

@Builder
public record DeviceDto (
        Long id,
        String deviceName,
        String imgUrl,
        String regDate,
        DeviceStatus status
) {
}
