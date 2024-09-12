package com.keepgoing.keepserver.global.exception.notice;


import com.keepgoing.keepserver.global.exception.BusinessException;

public class NoticeException extends BusinessException {
    private static final NoticeException USER_NOT_TEACHER = new NoticeException(NoticeError.USER_NOT_TEACHER);

    public NoticeException(NoticeError error) {
        super(error);
    }

    public static NoticeException userNotTeacher(){
        return USER_NOT_TEACHER;
    }
}
