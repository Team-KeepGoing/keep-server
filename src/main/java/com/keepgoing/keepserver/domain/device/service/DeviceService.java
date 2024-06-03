package com.keepgoing.keepserver.domain.device.service;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.device.payload.response.MyDevicesDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.security.core.Authentication;

public interface DeviceService {

    BaseResponse myDevices(Authentication authentication);

    BaseResponse deviceCreate(DeviceDto deviceDto);

    BaseResponse deviceRead(Long id);

    BaseResponse deleteDevice(Long id, Authentication authentication);

    BaseResponse findAll();

    default DeviceResponseDto entityToDto(Device entity) {
        return DeviceResponseDto.builder()
                .id(entity.getId())
                .deviceName((entity.getDeviceName()))
                .imgUrl(entity.getImgUrl())
                .status(entity.isStatus())
                .build();
    }

    default Device dtoToEntity(DeviceDto dto) {
        return Device.builder()
                .deviceName((dto.deviceName()))
                .imgUrl(dto.imgUrl())
                .status(dto.status())
                .build();
    }

    default MyDevicesDto entityToDto(MyDevicesDto dto) {
        return MyDevicesDto.builder()
                .id(dto.id())
                .deviceName(dto.deviceName())
                .build();
    }
}