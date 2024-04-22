package com.keepgoing.keepserver.domain.device.service;

import com.keepgoing.keepserver.domain.device.entity.device.Device;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.global.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface DeviceService {

    ResponseEntity<BaseResponse> myDevices(Authentication authentication);

    ResponseEntity<BaseResponse> deviceRead(Long id);
    ResponseEntity<BaseResponse> deleteDevice(Long id, Authentication authentication);

    ResponseEntity<BaseResponse> findAll();



    default Device dtoToEntity(DeviceDto dto){
        return Device.builder()
                .deviceName(dto.getDeviceName())
                .status(dto.getStatus())
                .imgUrl(dto.getImgUrl())
                .build();
    }

    default DeviceResponseDto entityToDto(Device entity){
        return DeviceResponseDto.builder()
                .id(entity.getId())
                .deviceName((entity.getDeviceName()))
                .status(entity.getStatus())
                .build();
    }
}
