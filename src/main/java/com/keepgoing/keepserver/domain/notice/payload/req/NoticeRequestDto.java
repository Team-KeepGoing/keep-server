package com.keepgoing.keepserver.domain.notice.payload.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record NoticeRequestDto(
        @NotBlank(message = "메시지는 필수입니다.")
        String message,
        @NotNull(message = "isGlobal 값은 필수입니다.")
        boolean isGlobal,
        List<Long> userIds
) {
}
