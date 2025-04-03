package com.keepgoing.keepserver.global.exception.teacher;

import com.keepgoing.keepserver.global.exception.BusinessException;

public class ItemException extends BusinessException {
    private static final ItemException ITEM_NOT_FOUND_EXCEPTION = new ItemException(ItemError.ITEM_NOT_FOUND_EXCEPTION);

    public ItemException(ItemError error) {
        super(error);
    }

    public static ItemException itemNotFound() {
        return ITEM_NOT_FOUND_EXCEPTION;
    }
}
