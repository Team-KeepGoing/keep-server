package com.keepgoing.keepserver.domain.returns.service;

import com.keepgoing.keepserver.global.common.BaseResponse;

public interface ReturnService {
    BaseResponse returnDevice(String deviceName, String email);
    BaseResponse returnBook(String nfcCode, String email);
}