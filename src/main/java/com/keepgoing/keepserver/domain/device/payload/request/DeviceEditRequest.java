package com.keepgoing.keepserver.domain.device.payload.request;

import com.keepgoing.keepserver.domain.device.enums.DeviceStatus;

public record DeviceEditRequest (
        String deviceName,
        String imgUrl,
        DeviceStatus status
) {
}