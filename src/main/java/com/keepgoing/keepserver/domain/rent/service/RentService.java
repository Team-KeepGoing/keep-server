package com.keepgoing.keepserver.domain.rent.service;

import com.keepgoing.keepserver.global.common.BaseResponse;

public interface RentService {
    BaseResponse rentDevice(String deviceName, String email);
    BaseResponse rentBook(String nfcCode, String email);
}
