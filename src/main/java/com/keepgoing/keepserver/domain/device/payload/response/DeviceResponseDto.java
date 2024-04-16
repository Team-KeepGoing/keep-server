package com.keepgoing.keepserver.domain.device.payload.response;

import lombok.Builder;

@Builder
public record DeviceResponseDto(
        Long id,
        String device_name,
        String category,
        int status) {
}
