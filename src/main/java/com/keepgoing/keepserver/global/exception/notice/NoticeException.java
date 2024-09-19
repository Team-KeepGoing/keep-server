package com.keepgoing.keepserver.global.exception.notice;


import com.keepgoing.keepserver.global.exception.BusinessException;

public class NoticeException extends BusinessException {
    private static final NoticeException USER_CANNOT_DELETE = new NoticeException(NoticeError.USER_CANNOT_DELETE);

    public NoticeException(NoticeError error) {
        super(error);
    }

    public static NoticeException userCannotDelete(){
        return USER_CANNOT_DELETE;
    }
}
