package com.keepgoing.keepserver.global.exception.book;

import com.keepgoing.keepserver.global.exception.BusinessException;

public class BookException extends BusinessException {
    private static final BookException USER_NOT_FOUND = new BookException(BookError.USER_NOT_FOUND);

    private static final BookException BOOK_NOT_FOUND_EXCEPTION = new BookException(BookError.BOOK_NOT_FOUND_EXCEPTION);
    private static final BookException BOOK_NOT_AVAILABLE = new BookException(BookError.BOOK_NOT_AVAILABLE);

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
}
