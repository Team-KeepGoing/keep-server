package com.keepgoing.keepserver.domain.user.service.user;

import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import com.keepgoing.keepserver.domain.user.dto.UserDto;
import com.keepgoing.keepserver.domain.user.dto.UserNoticesDto;
import com.keepgoing.keepserver.domain.user.dto.request.SignupRequest;
import com.keepgoing.keepserver.domain.user.dto.request.StatusRequest;
import com.keepgoing.keepserver.domain.user.dto.request.UserInfoRequest;
import com.keepgoing.keepserver.domain.user.dto.response.ApiResponse;
import com.keepgoing.keepserver.domain.user.dto.response.JwtResponse;
import com.keepgoing.keepserver.global.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserService {
    ApiResponse<JwtResponse> registerUser(SignupRequest signupRequest) throws BusinessException;

    ResponseEntity<String> updateUserData(UserInfoRequest request, Authentication authentication);

    UserDto provideUserInfo(Authentication authentication);

    JwtResponse authenticateAndGenerateJWT(String email, String password);

    UserNoticesDto getNoticeByUser(Authentication authentication);

    User getTeacher(Authentication authentication);

    ResponseEntity<String> updateUserStatus(StatusRequest statusRequest, Authentication authentication);
}
