package com.keepgoing.keepserver.domain.notice.payload.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record NoticeRequestDto(
        @NotBlank(message = "메시지는 필수입니다.")
        String message,
        @NotBlank(message = "isGlobal 값은 필수입니다.")
        boolean isGlobal,
        List<Long> userIds
) {
}
