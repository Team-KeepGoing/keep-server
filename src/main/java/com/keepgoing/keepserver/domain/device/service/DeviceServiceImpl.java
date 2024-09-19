package com.keepgoing.keepserver.domain.device.service;

import com.keepgoing.keepserver.domain.device.domain.entity.Device;
import com.keepgoing.keepserver.domain.device.domain.repository.DeviceRepository;
import com.keepgoing.keepserver.domain.device.mapper.DeviceMapper;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceDto;
import com.keepgoing.keepserver.domain.device.payload.request.DeviceEditRequest;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import com.keepgoing.keepserver.domain.user.domain.repository.user.UserRepository;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.device.DeviceError;
import com.keepgoing.keepserver.global.exception.device.DeviceException;
import com.keepgoing.keepserver.global.exception.user.UserError;
import com.keepgoing.keepserver.global.exception.user.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public BaseResponse findAll() {
        List<Device> devices = deviceRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<DeviceResponseDto> dtos = deviceMapper.convertDevicesToDtos(devices);
        return new BaseResponse(HttpStatus.OK, "모든 기기 불러오기 성공", dtos);
    }

    @Override
    public BaseResponse deviceCreate(DeviceDto deviceDto) {
        deviceRepository.save(deviceMapper.dtoToEntity(deviceDto));
        return new BaseResponse(HttpStatus.OK, "기기 생성 성공");
    }

    @Override
    public BaseResponse deviceRead(Long id) {
        Device device = deviceRepository.findById(id).orElseThrow(DeviceException::notFoundDevice);
        return new BaseResponse(HttpStatus.OK, "기기 조회 성공", deviceMapper.entityToDto(device));
    }

    @Override
    public BaseResponse deleteDevice(Long id, Authentication authentication) {
        User user = findUserByEmail(authentication.getName());
        userRepository.findByIdAndTeacherIsTrue(user.getId())
                      .orElseThrow(() -> new UserException(UserError.USER_NOT_TEACHER));
        deleteDeviceById(id);
        return new BaseResponse(HttpStatus.OK, "기기 삭제 성공");
    }

    @Override
    public BaseResponse myDevices(Authentication authentication) {
        User user = findUserByEmail(authentication.getName());
        List<Device> devices = findDevicesBorrowedByUser(user);
        List<DeviceResponseDto> deviceResponseDtos = deviceMapper.convertDevicesToDtos(devices);
        return new BaseResponse(HttpStatus.OK, "유저가 대여한 기기 목록 조회 성공", deviceResponseDtos);
    }

    @Override
    public BaseResponse editDevice(Long id, DeviceEditRequest deviceEditRequest) {

        Device device = findDeviceById(id);
        updateDevice(device, deviceEditRequest);
        deviceRepository.save(device);

        return new BaseResponse(HttpStatus.OK, "해당 기기 정보 수정 성공");
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(DeviceException::userNotFound);
    }

    private Device findDeviceById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(DeviceException::notFoundDevice);
    }

    public List<Device> findDevicesBorrowedByUser(User user) {
        return deviceRepository.findByBorrower(user);
    }

    private void deleteDeviceById(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new DeviceException(DeviceError.DEVICE_NOT_FOUND_EXCEPTION);
        }
        deviceRepository.deleteById(id);
    }

    private void updateDevice(Device device, DeviceEditRequest request) {
        if (request.deviceName() != null) device.setDeviceName(request.deviceName());
        if (request.imgUrl() != null) device.setImgUrl(request.imgUrl());
        if (request.status() != null) device.setStatus(request.status());
    }
}