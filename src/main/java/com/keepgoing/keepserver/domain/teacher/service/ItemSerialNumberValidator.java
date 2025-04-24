package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.domain.teacher.domain.repository.ItemRepository;
import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemSerialNumberValidator {
    public Map<String, Boolean> getSerialNumberMap(List<ItemExcelDto> dtos) {
        Map<String, Boolean> serialNums = new HashMap<>();
        for (var dto : dtos) {
            serialNums.put(dto.serialNumber(), true);
        }
        return serialNums;
    }

    public void updateExistsBySerialNum(Map<String, Boolean> serialNums, ItemRepository itemRepository) {
        var exists = itemRepository.findAllBySerialNumberIn(serialNums.keySet());
        for (var item : exists) {
            serialNums.put(item.getSerialNumber(), false);
        }
    }

    public boolean isNewItem(Map<String, Boolean> serialNums, String serialNum) {
        return serialNums.getOrDefault(serialNum, false);
    }
}

