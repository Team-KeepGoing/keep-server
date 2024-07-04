package com.keepgoing.keepserver.domain.user.service.user;

import com.keepgoing.keepserver.domain.user.payload.request.SignupRequest;
import com.keepgoing.keepserver.domain.user.payload.request.UserInfoRequest;
import com.keepgoing.keepserver.domain.user.payload.request.UserProfileDto;
import com.keepgoing.keepserver.domain.user.payload.response.ApiResponse;
import com.keepgoing.keepserver.domain.user.payload.response.JwtResponse;
import com.keepgoing.keepserver.global.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserService {
    ApiResponse<JwtResponse> registerUser(SignupRequest signupRequest) throws BusinessException;
    ResponseEntity<String> updateUserData(UserInfoRequest request, Authentication authentication);
    UserProfileDto provideUserInfo(Authentication authentication);
    JwtResponse authenticateAndGenerateJWT(String email, String password);
}