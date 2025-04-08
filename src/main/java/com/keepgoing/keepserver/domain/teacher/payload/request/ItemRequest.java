package com.keepgoing.keepserver.domain.teacher.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ItemRequest (
        @NotNull
        @Size(min = 1, max = 10)
        String item,

        @NotNull
        String details,

        @NotNull
        String serialNumber,

        @NotNull
        LocalDateTime acquisitionDate,

        @NotNull
        Long price,

        @NotNull
        String place
) {}
