package com.keepgoing.keepserver.domain.user.dto.request;

import com.keepgoing.keepserver.domain.user.domain.enums.Status;
import jakarta.validation.constraints.NotBlank;

public record StatusRequest (
        @NotBlank(message = "Status 값은 필수입니다. (NORMAL/PAIN/HEALTHROOM/EMERGENCY")
        Status status
) {
}
