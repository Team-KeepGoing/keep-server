package com.keepgoing.keepserver.domain.user.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfoRequest {
    private String email;
    private String name;
}
