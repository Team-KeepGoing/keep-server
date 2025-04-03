package com.keepgoing.keepserver.domain.teacher.payload.request;

import java.time.LocalDateTime;

public record ItemRequest (
        String item,
        String serialNumber,
        LocalDateTime acquisitionDate,
        String price,
        String registerPerson
) {
}
