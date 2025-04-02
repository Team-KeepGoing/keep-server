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
                .serial_number(entity.getSerial_number())
                .acquisition_date(entity.getAcquisition_date())
                .price(entity.getPrice())
                .register_person(entity.getRegister_person())
                .usage_date(entity.getUsage_date())
                .memo(entity.getMemo())
                .build();
    }

    public Item dtoToEntity(ItemRequest dto) {
        return Item.builder()
                .item(dto.item())
                .serial_number(dto.serial_number())
                .acquisition_date(dto.acquisition_date())
                .price(dto.price())
                .register_person(dto.register_person())
                .build();
    }

    public List<ItemResponse> convertItemsToDtos(List<Item> items) {
        return items.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
