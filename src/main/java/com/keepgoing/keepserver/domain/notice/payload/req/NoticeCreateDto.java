package com.keepgoing.keepserver.domain.notice.payload.req;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class NoticeCreateDto {
    private final String message;
    private final boolean isGlobal;
    private final List<Long> userIds;
}
