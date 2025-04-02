package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.domain.repository.ItemRepository;
import com.keepgoing.keepserver.domain.teacher.mapper.ItemMapper;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemResponseDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
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
        List<ItemResponseDto> dtos = itemMapper.convertItemsToDtos(items);
        return new BaseResponse(HttpStatus.OK, "Successful query of managed items list", dtos);
    }

}
