package com.keepgoing.keepserver.domain.teacher.payload.request;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;

import java.time.LocalDateTime;

public record ItemUpdateRequest(
        String item,
        String details,
        String serialNumber,
        LocalDateTime acquisitionDate,
        Long price,
        String rentedBy,
        String place,
        LocalDateTime returnDate,
        LocalDateTime rentalDate,
        Long usageDate,
        ItemStatus status
) {}

