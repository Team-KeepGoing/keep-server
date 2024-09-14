package com.keepgoing.keepserver.domain.user.dto;

import com.keepgoing.keepserver.domain.notice.payload.res.NoticeResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserNoticesDto (
        Set<NoticeResponseDto> notices
) {
    @QueryProjection
    public UserNoticesDto{}
}
