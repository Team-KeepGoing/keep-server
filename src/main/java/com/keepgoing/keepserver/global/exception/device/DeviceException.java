package com.keepgoing.keepserver.global.exception.device;

import com.keepgoing.keepserver.global.exception.BusinessException;

public class DeviceException extends BusinessException {
    private static final DeviceException USER_NOT_FOUND = new DeviceException(DeviceError.USER_NOT_FOUND);

    private static final DeviceException DEVICE_NOT_FOUND_EXCEPTION = new DeviceException(DeviceError.DEVICE_NOT_FOUND_EXCEPTION);
    private static final DeviceException DEVICE_NOT_AVAILABLE = new DeviceException(DeviceError.DEVICE_NOT_AVAILABLE);
    private static final DeviceException INVALID_BORROWER = new DeviceException(DeviceError.INVALID_BORROWER);

    public DeviceException(DeviceError error) {
        super(error);
    }

    public static DeviceException userNotFound() {
        return USER_NOT_FOUND;
    }

    public static DeviceException notFoundDevice() {
        return DEVICE_NOT_FOUND_EXCEPTION;
    }
    public static DeviceException deviceNotAvailable() {
        return DEVICE_NOT_AVAILABLE;
    }
    public static DeviceException invalidborrower() {
        return INVALID_BORROWER;
    }
}
