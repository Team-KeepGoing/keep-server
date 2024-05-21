package com.keepgoing.keepserver.domain.user.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignupRequest {
    private String email;
    private String password;
    private String name;
    private boolean teacher;
}
