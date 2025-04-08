package com.keepgoing.keepserver.domain.teacher.payload.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ItemResponse(
        Long id,
        String item,
        String serialNumber,
        LocalDateTime acquisitionDate,
        String price,
        String user,
        String place,
        LocalDateTime returnDate,
        LocalDateTime rentalDate,
        Long usageDate,
        String status
) {
}
