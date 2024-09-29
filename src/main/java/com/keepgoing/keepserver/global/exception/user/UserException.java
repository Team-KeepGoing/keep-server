package com.keepgoing.keepserver.global.exception.user;

import com.keepgoing.keepserver.global.exception.BusinessException;
import com.keepgoing.keepserver.global.exception.error.ErrorProperty;

public class UserException extends BusinessException {
    private static final UserException USER_NOT_TEACHER = new UserException(UserError.USER_NOT_TEACHER);

    public UserException(ErrorProperty error) {
        super(error);
    }

    public static UserException userNotTeacher() {
        return USER_NOT_TEACHER;
    }
}