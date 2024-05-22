package com.keepgoing.keepserver.domain.device.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCreateRequest {
    private Long id;
    private String deviceName;
    private String imgUrl;
    private int status;
}
