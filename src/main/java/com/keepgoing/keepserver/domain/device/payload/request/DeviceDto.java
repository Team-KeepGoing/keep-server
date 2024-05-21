package com.keepgoing.keepserver.domain.device.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeviceDto {
    private Long id;
    private String deviceName;
    private String imgUrl;
    private int status;
}
