package com.keepgoing.keepserver.domain.device.payload.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeviceDto {
    private Long id;
    private String device_name;
    private String category;
    private String imgUrl;
    private int status;

}
