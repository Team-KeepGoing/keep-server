package com.keepgoing.keepserver.domain.user.dto.request;

import lombok.Builder;

@Builder
public record LoginRequest (
        String email,
        String password
) {
}
