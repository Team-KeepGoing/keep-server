package com.keepgoing.keepserver.domain.user.service.user;

import com.keepgoing.keepserver.domain.user.exception.CustomException;
import com.keepgoing.keepserver.domain.user.payload.request.SignupRequest;

public interface UserService {
    void registerUser(SignupRequest signupRequest) throws CustomException;
}