package com.keepgoing.keepserver.domain.device.service;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
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
    public BaseResponse deviceCreate(DeviceDto deviceDto) {
        deviceRepository.save(dtoToEntity(deviceDto));
        return new BaseResponse(HttpStatus.OK, "기기 생성 성공");
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

    @Override
    public BaseResponse myDevices(Authentication authentication) {
        User user = findUserByEmail(authentication.getName());
        List<Device> devices = findDevicesBorrowedByUser(user);
        List<DeviceResponseDto> deviceResponseDtos = convertDevicesToDtos(devices);

        return new BaseResponse(HttpStatus.OK, "유저가 대여한 기기 목록 조회 성공", deviceResponseDtos);
    }

    @Override
    public BaseResponse rentDevice(String deviceName, String email) {
        User user = findUserByEmail(email);
        Device device = findDeviceByName(deviceName);
        validateDeviceAvailability(device);
        device.setBorrower(user);
        rentDeviceToUser(device);

        return new BaseResponse(HttpStatus.OK, "기기 대여 성공", entityToDto(device));
    }

    public List<DeviceResponseDto> convertDevicesToDtos(List<Device> devices) {
        List<DeviceResponseDto> deviceResponseDtos = new ArrayList<>();
        for (Device device : devices) {
            DeviceResponseDto dto = entityToDto(device);
            deviceResponseDtos.add(dto);
        }
        return deviceResponseDtos;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new DeviceException(DeviceError.USER_NOT_FOUND));
    }

    public List<Device> findDevicesBorrowedByUser(User user) {
        return deviceRepository.findByBorrower(user);
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

    private Device findDeviceByName(String deviceName) {
        return deviceRepository.findByDeviceName(deviceName)
                .orElseThrow(DeviceException::notFoundDevice);
    }

    private void validateDeviceAvailability(Device device) {
        if (device.isStatus()) {
            throw DeviceException.deviceAlreadyRented();
        }
    }

    private void rentDeviceToUser(Device device) {
        device.setStatus(true);
        deviceRepository.save(device);
    }
}