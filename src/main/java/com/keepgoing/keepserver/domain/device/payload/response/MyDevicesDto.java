package com.keepgoing.keepserver.domain.device.payload.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public record MyDevicesDto (
        Long id,
        String deviceName ) {
}
