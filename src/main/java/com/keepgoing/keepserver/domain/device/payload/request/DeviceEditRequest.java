package com.keepgoing.keepserver.domain.device.payload.request;

import com.keepgoing.keepserver.domain.device.domain.enums.DeviceStatus;

public record DeviceEditRequest (
        String deviceName,
        String imgUrl,
        DeviceStatus status
) {
}
