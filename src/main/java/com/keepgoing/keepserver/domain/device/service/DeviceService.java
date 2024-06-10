package com.keepgoing.keepserver.domain.device.service;

import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.security.core.Authentication;

public interface DeviceService {

    BaseResponse myDevices(Authentication authentication);

    BaseResponse deviceCreate(DeviceDto deviceDto);

    BaseResponse deviceRead(Long id);

    BaseResponse deleteDevice(Long id, Authentication authentication);

    BaseResponse findAll();
}