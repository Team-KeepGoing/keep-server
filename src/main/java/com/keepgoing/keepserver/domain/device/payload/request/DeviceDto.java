package com.keepgoing.keepserver.domain.device.payload.request;

import lombok.Builder;

@Builder
public record DeviceDto (
        Long id,
        String deviceName,
        String imgUrl,
        int status
){
}
