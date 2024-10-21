package com.keepgoing.keepserver.global.exception.user;

import com.keepgoing.keepserver.global.exception.BusinessException;
import com.keepgoing.keepserver.global.exception.error.ErrorProperty;

public class UserException extends BusinessException {
    private static final UserException USER_NOT_TEACHER = new UserException(UserError.USER_NOT_TEACHER);
    private static final UserException USER_NOT_FOUND = new UserException(UserError.USER_NOT_FOUND);
    private static final UserException TEACHER_ACCOUNT_NOT_APPROVED = new UserException(UserError.TEACHER_ACCOUNT_NOT_APPROVED);

    public UserException(ErrorProperty error) {
        super(error);
    }

    public static UserException userNotTeacher() {
        return USER_NOT_TEACHER;
    }
    public static UserException userNotFound(){ return USER_NOT_FOUND; }
    public static UserException teacherAccountNotApproved() {return TEACHER_ACCOUNT_NOT_APPROVED; }
}
