package com.keepgoing.keepserver.domain.teacher.mapper;

import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResponse entityToDto(Item entity);
    Item dtoToEntity(ItemRequest dto);

    List<ItemResponse> convertItemsToDtos(List<Item> items);
}
