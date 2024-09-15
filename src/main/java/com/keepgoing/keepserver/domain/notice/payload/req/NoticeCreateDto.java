package com.keepgoing.keepserver.domain.notice.payload.req;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import lombok.Builder;

import java.util.List;

@Builder
public record NoticeCreateDto (
        String message,
        boolean isGlobal,
        List<Long> userIds
) {

    public Notice toEntity(User teacher) {
        return Notice.builder()
              .isGlobal(isGlobal)
              .teacher(teacher)
              .message(message)
              .build();
    }

}
