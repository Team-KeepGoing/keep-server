package com.keepgoing.keepserver.domain.teacher.payload.request;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public record ItemUpdateRequest(
        String item,
        String details,
        String serialNumber,
        LocalDateTime acquisitionDate,
        Integer price,
        String rentedBy,
        String place,
        LocalDateTime returnDate,
        LocalDateTime rentalDate,
        LocalDateTime usageDate,
        ItemStatus status
) {}

