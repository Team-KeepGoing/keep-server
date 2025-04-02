package com.keepgoing.keepserver.domain.teacher.mapper;

import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public ItemResponseDto entityToDto(Item entity) {
        return ItemResponseDto.builder()
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

    public List<ItemResponseDto> convertItemsToDtos(List<Item> items) {
        return items.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
