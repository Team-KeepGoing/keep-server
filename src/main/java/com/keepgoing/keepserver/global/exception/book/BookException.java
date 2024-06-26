package com.keepgoing.keepserver.global.exception.book;

import com.keepgoing.keepserver.global.exception.BusinessException;

public class BookException extends BusinessException {
    private static final BookException USER_NOT_FOUND = new BookException(BookError.USER_NOT_FOUND);

    private static final BookException BOOK_NOT_FOUND_EXCEPTION = new BookException(BookError.BOOK_NOT_FOUND_EXCEPTION);
    private static final BookException BOOK_NOT_AVAILABLE = new BookException(BookError.BOOK_NOT_AVAILABLE);
    private static final BookException IMAGE_UPLOAD_FAILED = new BookException(BookError.IMAGE_UPLOAD_FAILED);
    private static final BookException INVALID_BORROWER = new BookException(BookError.INVALID_BORROWER);

    public BookException(BookError error) {
        super(error);
    }

    public static BookException userNotFound() {
        return USER_NOT_FOUND;
    }

    public static BookException notFoundBook() {
        return BOOK_NOT_FOUND_EXCEPTION;
    }
    public static BookException bookNotAvailable() {
        return BOOK_NOT_AVAILABLE;
    }
    public static BookException imageUploadFailed() {
        return IMAGE_UPLOAD_FAILED;
    }
    public static BookException invalidborrower() {
        return INVALID_BORROWER;
    }
}
