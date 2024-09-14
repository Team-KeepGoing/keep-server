package com.keepgoing.keepserver.domain.notice.payload.res;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NoticeResponseDto (
        long id,
        String message,
        String teacherName,
        LocalDateTime createTime,
        LocalDateTime editTime
) {
    @QueryProjection
    public NoticeResponseDto{}
}
