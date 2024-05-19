package com.keepgoing.keepserver.domain.device.service;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.device.repository.DeviceRepository;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import com.keepgoing.keepserver.domain.user.repository.user.UserRepository;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.device.DeviceError;
import com.keepgoing.keepserver.global.exception.device.DeviceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
    public BaseResponse findAll() {
        List<Device> devices = deviceRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<DeviceResponseDto> dtos = devices.stream()
                .map(this::entityToDto)
                .toList();

        return new BaseResponse(HttpStatus.OK, "모든 기기 불러오기 성공", dtos);
    }

    @Override
    public BaseResponse deviceRead(Long id) {
        Device device = deviceRepository.findById(id).orElseThrow(DeviceException::notFoundDevice);

        return new BaseResponse(HttpStatus.OK, "기기 조회 성공", entityToDto(device));
    }
    
    @Override
    public BaseResponse deleteDevice(Long id, Authentication authentication) {
        User user = findUserByEmail(authentication.getName());
        validateTeacher(user);
        deleteDeviceById(id);

        return new BaseResponse(HttpStatus.OK, "기기 삭제 성공");
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new DeviceException(DeviceError.USER_NOT_FOUND));
    }

    private void validateTeacher(User user) {
        if (!user.isTeacher()) {
            throw new DeviceException(DeviceError.USER_NOT_FOUND);
        }
    }

    private void deleteDeviceById(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new DeviceException(DeviceError.DEVICE_NOT_FOUND_EXCEPTION);
        }
        deviceRepository.deleteById(id);
    }


    @Override
    public BaseResponse myDevices(Authentication authentication) {
        String userName = userRepository.findByEmail(authentication.getName()).orElseThrow(DeviceException::userNotFound).getEmail();
        List<Device> devices = deviceRepository.findByDeviceNameContaining(userName, (Sort.by(Sort.Direction.DESC, "id")));

        List<DeviceResponseDto> deviceResponseDtos = new ArrayList<>(devices.stream()
                .map(this::entityToDto)
                .toList());

        return new BaseResponse(HttpStatus.OK, "기기 불러오기 성공", deviceResponseDtos);
    }
}