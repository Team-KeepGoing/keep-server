package com.keepgoing.keepserver.domain.device.service;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceCreateRequest;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseEntity;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface DeviceService {

    BaseResponse myDevices(Authentication authentication);
    BaseResponse deviceCreate(DeviceCreateRequest deviceCreateRequest,
                              MultipartFile multipartFile,
                              Authentication authentication);
    BaseResponse deviceRead(Long id);
    BaseResponse deleteDevice(Long id, Authentication authentication);

    BaseResponse findAll();

    default DeviceResponseDto entityToDto(Device entity){
        return DeviceResponseDto.builder()
                .id(entity.getId())
                .deviceName((entity.getDeviceName()))
                .imgUrl(entity.getImgUrl())
                .status(entity.getStatus())
                .build();
    }

    default DeviceResponseEntity dtoToEntity(DeviceDto dto){
        return DeviceResponseEntity.builder()
                .id(dto.getId())
                .deviceName((dto.getDeviceName()))
                .imgUrl(dto.getImgUrl())
                .status(dto.getStatus())
                .build();
    }
}