package com.keepgoing.keepserver.domain.Return.service;

import com.keepgoing.keepserver.global.common.BaseResponse;

public interface ReturnService {
    BaseResponse returnDevice(String deviceName, String email);
    BaseResponse returnBook(String bookName, String email);
}