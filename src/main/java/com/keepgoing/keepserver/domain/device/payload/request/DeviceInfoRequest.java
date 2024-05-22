package com.keepgoing.keepserver.domain.device.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfoRequest {
    private Long id;
    private String deviceName;
    private String imgUrl;
    private int status;
}
