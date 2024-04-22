package com.keepgoing.keepserver.domain.device.exception;

import com.keepgoing.keepserver.domain.device.exception.device.DeviceError;
import com.keepgoing.keepserver.global.exception.BusinessException;

public class DeviceException extends BusinessException {
    private static final DeviceException USER_NOT_FOUND = new DeviceException(DeviceError.USER_NOT_FOUND);

    private static final DeviceException DEVICE_NOT_FOUND_EXCEPTION = new DeviceException(DeviceError.DEVICE_NOT_FOUND_EXCEPTION);

    public DeviceException(DeviceError error) {
        super(error);
    }
    public static DeviceException userNotFound() {
        return USER_NOT_FOUND;
    }

    public static DeviceException notFoundDevice(){
        return DEVICE_NOT_FOUND_EXCEPTION;
    }
}