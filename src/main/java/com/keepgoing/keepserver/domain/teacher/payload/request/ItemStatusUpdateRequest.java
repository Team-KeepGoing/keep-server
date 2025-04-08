package com.keepgoing.keepserver.domain.teacher.payload.request;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;

public record ItemStatusUpdateRequest(
        Long itemId,
        ItemStatus status
) {}

