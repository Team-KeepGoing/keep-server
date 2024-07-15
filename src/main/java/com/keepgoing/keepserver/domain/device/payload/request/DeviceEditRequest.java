package com.keepgoing.keepserver.domain.device.payload.request;

import com.keepgoing.keepserver.domain.device.domain.entity.enums.DeviceStatus;

public record DeviceEditRequest (
        String deviceName,
        String imgUrl,
        DeviceStatus status
) {
}
