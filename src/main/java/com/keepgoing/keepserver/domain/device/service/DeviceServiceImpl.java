package com.keepgoing.keepserver.domain.device.service;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.device.repository.DeviceRepository;
import com.keepgoing.keepserver.domain.user.repository.user.UserRepository;
import com.keepgoing.keepserver.global.dto.response.BaseResponse;
import com.keepgoing.keepserver.global.exception.DeviceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    @Override
    public ResponseEntity<BaseResponse> findAll() {
        BaseResponse baseResponse = new BaseResponse();

        List<Device> devices = deviceRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<DeviceResponseDto> dtos = devices.stream()
                .map(this::entityToDto)
                .toList();

        baseResponse.of(HttpStatus.OK, "모든 기기 불러오기 성공", dtos);

        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public Device dtoToEntity(DeviceDto dto) {
        return DeviceService.super.dtoToEntity(dto);
    }

    @Override
    public DeviceResponseDto entityToDto(Device entity) {
        return DeviceService.super.entityToDto(entity);
    }

    @Override
    public ResponseEntity<BaseResponse> deviceRead(Long id) {
        BaseResponse baseResponse = new BaseResponse();

        Device device = deviceRepository.findById(id).orElseThrow(DeviceException::notFoundDevice);

        baseResponse.of(HttpStatus.OK, "기기 조회 성공", entityToDto(device));
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public ResponseEntity<BaseResponse> deleteDevice(Long id, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> myDevices(Authentication authentication) {
        BaseResponse baseResponse = new BaseResponse();

        String userName = userRepository.findByEmail(authentication.getName()).orElseThrow(DeviceException::userNotFound).getEmail();
        List<Device> devices = deviceRepository.findByDeviceNameContaining(userName, (Sort.by(Sort.Direction.DESC, "id")));

        List<DeviceResponseDto> deviceResponseDtos = new ArrayList<>(devices.stream()
                .map(this::entityToDto)
                .toList());

        baseResponse.of(HttpStatus.OK, "기기 불러오기 성공", deviceResponseDtos);

        return ResponseEntity.ok(baseResponse);
    }
}
