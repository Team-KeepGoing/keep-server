package com.keepgoing.keepserver.domain.user.dto.request;

import lombok.Builder;

@Builder
public record SignupRequest(
        String email,
        String password,
        String name,
        boolean isTeacher,
        boolean isApproved
) {
}
