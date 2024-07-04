package com.keepgoing.keepserver.domain.user.service.user;

import com.keepgoing.keepserver.domain.user.payload.request.SignupRequest;
import com.keepgoing.keepserver.domain.user.payload.request.UserInfoRequest;
import com.keepgoing.keepserver.domain.user.payload.request.UserProfileDto;
import com.keepgoing.keepserver.domain.user.payload.response.JwtResponse;
import com.keepgoing.keepserver.global.exception.BusinessException;

public interface UserService {
    void registerUser(SignupRequest signupRequest) throws BusinessException;
    void updateUserData(UserInfoRequest request, String email);
    UserProfileDto provideUserInfo(String userEmail);
    JwtResponse authenticateAndGenerateJWT(String email, String password);
}