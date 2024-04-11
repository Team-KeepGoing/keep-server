package com.keepgoing.keepserver.domain.user.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignupRequest {

    private String email;

    private String password;

    private String name;
}
