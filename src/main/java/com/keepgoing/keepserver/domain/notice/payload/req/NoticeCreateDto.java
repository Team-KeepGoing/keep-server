package com.keepgoing.keepserver.domain.notice.payload.req;

import lombok.Builder;

import java.util.List;

@Builder
public record NoticeCreateDto (
        String message,
        boolean isGlobal,
        List<Long> userIds
) {
}
