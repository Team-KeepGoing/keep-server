package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemStatusUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemUpdateRequest;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {
    BaseResponse findAll();
    BaseResponse statusCount();
    BaseResponse readItem(Long id);
    BaseResponse createItem(ItemRequest request);
    BaseResponse updateItem(Long id, ItemUpdateRequest request);
    BaseResponse updateItemStatus(ItemStatusUpdateRequest request);
    BaseResponse importItemsFromExcel(MultipartFile file);
    BaseResponse validateItemsFromExcel(MultipartFile file);
    Resource downloadItemTemplateFile();
    Resource exportItemsToExcelFile();
}
