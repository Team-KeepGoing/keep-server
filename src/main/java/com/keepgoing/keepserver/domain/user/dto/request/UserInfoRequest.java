package com.keepgoing.keepserver.domain.user.dto.request;

import lombok.Builder;

@Builder
public record UserInfoRequest (
        String email,
        String name
){
}
