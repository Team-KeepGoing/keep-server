package com.keepgoing.keepserver.domain.teacher.payload.response;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ItemResponse(
        Long id,
        String item,
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
