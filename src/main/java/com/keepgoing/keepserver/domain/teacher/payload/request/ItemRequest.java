package com.keepgoing.keepserver.domain.teacher.payload.request;

import java.time.LocalDateTime;

public record ItemRequest (
        String item,
        String serial_number,
        LocalDateTime acquisition_date,
        String price,
        String register_person
) {
}
