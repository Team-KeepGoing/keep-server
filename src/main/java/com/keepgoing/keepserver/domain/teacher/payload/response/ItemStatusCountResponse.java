package com.keepgoing.keepserver.domain.teacher.payload.response;

public record ItemStatusCountResponse (
        Long totalItems,
        Long availableItems,
        Long inUseItems,
        Long unavailableItems
) {}
