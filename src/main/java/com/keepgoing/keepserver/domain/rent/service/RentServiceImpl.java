package com.keepgoing.keepserver.domain.rent.service;

import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.entity.DeviceStatus;
import com.keepgoing.keepserver.domain.device.mapper.DeviceMapper;
import com.keepgoing.keepserver.domain.device.repository.DeviceRepository;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import com.keepgoing.keepserver.domain.user.service.user.UserServiceImpl;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.device.DeviceError;
import com.keepgoing.keepserver.global.exception.device.DeviceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService{
    private final UserServiceImpl userService;
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public BaseResponse rentDevice(String deviceName, String email) {
        User user = userService.findUserByEmail(email);
        Device device = findDeviceByName(deviceName);
        validateDeviceAvailability(device);
        rentDeviceToUser(device, user);
        return new BaseResponse(HttpStatus.OK, "기기 대여 성공", deviceMapper.entityToDto(device));
    }

    public Device findDeviceByName(String deviceName) {
        return deviceRepository.findByDeviceName(deviceName)
                .orElseThrow(DeviceException::notFoundDevice);
    }

    public void validateDeviceAvailability(Device device) {
        if (device.getStatus() != DeviceStatus.AVAILABLE) {
            throw new DeviceException(DeviceError.DEVICE_NOT_AVAILABLE);
        }
    }

    public void rentDeviceToUser(Device device, User user) {
        device.setBorrower(user);
        device.setStatus(DeviceStatus.RENTED);
        deviceRepository.save(device);
    }
}
