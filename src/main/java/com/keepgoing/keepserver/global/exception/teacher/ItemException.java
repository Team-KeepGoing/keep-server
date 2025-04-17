package com.keepgoing.keepserver.global.exception.teacher;

import com.keepgoing.keepserver.global.exception.BusinessException;

public class ItemException extends BusinessException {
    private static final ItemException ITEM_NOT_FOUND_EXCEPTION = new ItemException(ItemError.ITEM_NOT_FOUND_EXCEPTION);
    private static final ItemException ITEM_SERIAL_NUM_EXIST = new ItemException(ItemError.ITEM_SERIAL_NUM_EXIST);

    public ItemException(ItemError error) {
        super(error);
    }

    public static ItemException itemNotFound() {
        return ITEM_NOT_FOUND_EXCEPTION;
    }

    public static ItemException itemSerialNumExist(){
        return ITEM_SERIAL_NUM_EXIST;
    }
}
