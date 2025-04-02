package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.global.common.BaseResponse;

public interface ItemService {
    BaseResponse findAll();
    BaseResponse createItem(ItemRequest request);
}
