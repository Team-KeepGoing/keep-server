package com.keepgoing.keepserver.domain.teacher.mapper;

import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.item", target = "item")
    @Mapping(source = "entity.serialNumber", target = "serialNumber")
    @Mapping(source = "entity.acquisitionDate", target = "acquisitionDate")
    @Mapping(source = "entity.price", target = "price")
    @Mapping(source = "entity.registerPerson", target = "registerPerson")
    @Mapping(source = "entity.usageDate", target = "usageDate")
    @Mapping(source = "entity.memo", target = "memo")
    ItemResponse entityToDto(Item entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "dto.item", target = "item")
    @Mapping(source = "dto.serialNumber", target = "serialNumber")
    @Mapping(source = "dto.acquisitionDate", target = "acquisitionDate")
    @Mapping(source = "dto.price", target = "price")
    @Mapping(source = "dto.registerPerson", target = "registerPerson")
    Item dtoToEntity(ItemRequest dto);

    List<ItemResponse> convertItemsToDtos(List<Item> items);
}
