package com.keepgoing.keepserver.domain.user.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {

    private String email;

    private String name;

}
