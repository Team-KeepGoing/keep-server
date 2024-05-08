package com.keepgoing.keepserver.domain.device.service;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.global.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface DeviceService {

    ResponseEntity<BaseResponse> myDevices(Authentication authentication);

    ResponseEntity<BaseResponse> deviceRead(Long id);
    BaseResponse deleteDevice(Long id, Authentication authentication);

    ResponseEntity<BaseResponse> findAll();

    default DeviceResponseDto entityToDto(Device entity){
        return DeviceResponseDto.builder()
                .id(entity.getId())
                .deviceName((entity.getDeviceName()))
                .status(entity.getStatus())
                .build();
    }
}
