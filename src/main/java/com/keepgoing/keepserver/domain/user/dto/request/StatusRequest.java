package com.keepgoing.keepserver.domain.user.dto.request;

import com.keepgoing.keepserver.domain.user.domain.enums.Status;

public record StatusRequest (
        Status status
) {
}
