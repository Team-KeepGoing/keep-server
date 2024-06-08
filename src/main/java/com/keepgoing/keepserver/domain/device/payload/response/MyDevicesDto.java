package com.keepgoing.keepserver.domain.device.payload.response;

import lombok.Builder;

@Builder
public record MyDevicesDto (
        Long id,
        String deviceName ) {
}
