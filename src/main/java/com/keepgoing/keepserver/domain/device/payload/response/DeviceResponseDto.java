package com.keepgoing.keepserver.domain.device.payload.response;

import lombok.Builder;

@Builder
public record DeviceResponseDto (
        Long id,
        String deviceName,
        String imgUrl,
        boolean status
) {
}
