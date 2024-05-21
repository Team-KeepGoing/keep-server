package com.keepgoing.keepserver.domain.user.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
    private String email;
    private String password;
}
