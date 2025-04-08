package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import com.keepgoing.keepserver.domain.teacher.domain.repository.ItemRepository;
import com.keepgoing.keepserver.domain.teacher.mapper.ItemMapper;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemStatusUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemResponse;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemStatusCountResponse;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.teacher.ItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    @Transactional(readOnly = true)
    public BaseResponse findAll() {
        List<Item> items = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ItemResponse> dtos = itemMapper.convertItemsToDtos(items);
        return new BaseResponse(HttpStatus.OK, "Successful query of managed items list", dtos);
    }

    @Override
    @Transactional
    public BaseResponse createItem(ItemRequest request) {
        itemRepository.save(itemMapper.dtoToEntity(request));
        return new BaseResponse(HttpStatus.CREATED, "Successful creation of managed items");
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse readItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemException::itemNotFound);
        return new BaseResponse(HttpStatus.OK, "Successful query of managed item details", itemMapper.entityToDto(item));
    }

    @Override
    @Transactional
    public BaseResponse updateItem(Long id, ItemUpdateRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(ItemException::itemNotFound);

        itemMapper.updateItem(item, request);

        return new BaseResponse(HttpStatus.OK, "Item information has been updated successfully.");
    }


    @Override
    @Transactional(readOnly = true)
    public BaseResponse statusCount() {
        ItemStatusCountResponse response = getItemStatusCount();
        return new BaseResponse(HttpStatus.OK, "Item status count retrieved successfully", response);
    }

    @Override
    @Transactional
    public BaseResponse updateItemStatus(ItemStatusUpdateRequest request) {
        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(ItemException::itemNotFound);

        item.updateStatus(request.status());

        return new BaseResponse(HttpStatus.OK, "Item status has been changed successfully");
    }


    private ItemStatusCountResponse getItemStatusCount() {
        return new ItemStatusCountResponse(
                itemRepository.count(),
                itemRepository.countByStatus(ItemStatus.AVAILABLE),
                itemRepository.countByStatus(ItemStatus.IN_USE),
                itemRepository.countByStatus(ItemStatus.UNAVAILABLE)
        );
    }

}
