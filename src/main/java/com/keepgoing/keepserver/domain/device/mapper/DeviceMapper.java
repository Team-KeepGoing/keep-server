package com.keepgoing.keepserver.domain.device.mapper;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.enums.DeviceStatus;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeviceMapper {
    public static DeviceResponseDto entityToDto(Device entity) {
        return DeviceResponseDto.builder()
                .id(entity.getId())
                .deviceName(entity.getDeviceName())
                .imgUrl(entity.getImgUrl())
                .rentDate(entity.getRentDate())
                .status(entity.getStatus())
                .build();
    }

    public static Device dtoToEntity(DeviceDto dto) {
        return Device.builder()
                .deviceName(dto.deviceName())
                .imgUrl(dto.imgUrl())
                .rentDate(dto.rentDate())
                .status(DeviceStatus.AVAILABLE)
                .build();
    }

    public static List<DeviceResponseDto> convertDevicesToDtos(List<Device> devices) {
        return devices.stream()
                .map(DeviceMapper::entityToDto)
                .collect(Collectors.toList());
    }
}