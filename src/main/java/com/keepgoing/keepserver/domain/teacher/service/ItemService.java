package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemStatusUpdateRequest;
import com.keepgoing.keepserver.global.common.BaseResponse;

public interface ItemService {
    BaseResponse findAll();
    BaseResponse statusCount();
    BaseResponse readItem(Long id);
    BaseResponse createItem(ItemRequest request);
    BaseResponse updateItem(Long id, ItemRequest request);
    BaseResponse updateItemStatus(ItemStatusUpdateRequest request);
}
