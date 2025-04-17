package com.keepgoing.keepserver.domain.teacher.payload.request;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;

import java.time.LocalDate;

public record ItemUpdateRequest(
        String item,
        String details,
        String serialNumber,
        LocalDate acquisitionDate,
        Long price,
        String rentedBy,
        String place,
        LocalDate returnDate,
        LocalDate rentalDate,
        Long usageDate,
        ItemStatus status
) {}

