package com.keepgoing.keepserver.domain.user.dto;

import com.keepgoing.keepserver.domain.notice.payload.res.NoticeResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record UserNoticesDto (
        List<NoticeResponseDto> notices
) {
}
