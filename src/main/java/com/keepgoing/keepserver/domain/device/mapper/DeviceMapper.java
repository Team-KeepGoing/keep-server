package com.keepgoing.keepserver.domain.device.mapper;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.entity.DeviceStatus;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceMapper {
    public static DeviceResponseDto entityToDto(Device entity) {
        return DeviceResponseDto.builder()
                .id(entity.getId())
                .deviceName(entity.getDeviceName())
                .imgUrl(entity.getImgUrl())
                .status(entity.getStatus())
                .build();
    }

    public static Device dtoToEntity(DeviceDto dto) {
        return Device.builder()
                .deviceName(dto.deviceName())
                .imgUrl(dto.imgUrl())
                .status(DeviceStatus.AVAILABLE)
                .build();
    }

    public static List<DeviceResponseDto> convertDevicesToDtos(List<Device> devices) {
        return devices.stream()
                .map(DeviceMapper::entityToDto)
                .collect(Collectors.toList());
    }
}