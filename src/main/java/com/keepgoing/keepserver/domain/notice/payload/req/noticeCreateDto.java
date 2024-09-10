package com.keepgoing.keepserver.domain.notice.payload.req;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class noticeCreateDto {
    private final String content;
    private final char isGlobalYN;

}
