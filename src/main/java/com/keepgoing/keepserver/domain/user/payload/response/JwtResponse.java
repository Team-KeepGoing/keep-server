package com.keepgoing.keepserver.domain.user.payload.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
    private Long id;
    private String email;
    private String password;
    private boolean teacher;

    public static JwtResponse setJwtResponse(String token, Long id, String email, String password, boolean teacher) {
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.token = token;
        jwtResponse.id = id;
        jwtResponse.email = email;
        jwtResponse.password = password;
        jwtResponse.teacher = teacher;
        return jwtResponse;
    }
}