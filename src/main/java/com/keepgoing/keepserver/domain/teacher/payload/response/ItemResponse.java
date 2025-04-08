package com.keepgoing.keepserver.domain.teacher.payload.response;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ItemResponse(
        Long id,
        String item,
        String serialNumber,
        LocalDateTime acquisitionDate,
        String price,
        String rentedBy,
        String place,
        LocalDateTime returnDate,
        LocalDateTime rentalDate,
        Long usageDate,
        ItemStatus status
) { }
