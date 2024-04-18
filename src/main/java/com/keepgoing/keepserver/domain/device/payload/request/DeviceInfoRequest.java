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

    /* 기기 이름 */
    private String deviceName;

    /* 기기 사진 */
    private String imgUrl;

    /* 기기 대여 상태 */
    private int status;
}
