package com.keepgoing.keepserver.domain.teacher.mapper;

import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public ItemResponse entityToDto(Item entity) {
        return ItemResponse.builder()
                .id(entity.getId())
                .item(entity.getItem())
                .serialNumber(entity.getSerialNumber())
                .acquisitionDate(entity.getAcquisitionDate())
                .price(entity.getPrice())
                .registerPerson(entity.getRegisterPerson())
                .usageDate(entity.getUsageDate())
                .memo(entity.getMemo())
                .build();
    }

    public Item dtoToEntity(ItemRequest dto) {
        return Item.builder()
                .item(dto.item())
                .serialNumber(dto.serialNumber())
                .acquisitionDate(dto.acquisitionDate())
                .price(dto.price())
                .registerPerson(dto.registerPerson())
                .build();
    }

    public List<ItemResponse> convertItemsToDtos(List<Item> items) {
        return items.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
