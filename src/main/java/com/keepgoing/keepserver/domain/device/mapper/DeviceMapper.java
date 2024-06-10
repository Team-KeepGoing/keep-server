package com.keepgoing.keepserver.domain.device.mapper;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.entity.DeviceStatus;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeviceMapper {
    public DeviceResponseDto entityToDto(Device entity) {
        return DeviceResponseDto.builder()
                .id(entity.getId())
                .deviceName(entity.getDeviceName())
                .imgUrl(entity.getImgUrl())
                .status(entity.getStatus())
                .build();
    }

    public Device dtoToEntity(DeviceDto dto) {
        return Device.builder()
                .deviceName(dto.deviceName())
                .imgUrl(dto.imgUrl())
                .status(DeviceStatus.AVAILABLE)
                .build();
    }

    public List<DeviceResponseDto> convertDevicesToDtos(List<Device> devices) {
        return devices.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}