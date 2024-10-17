package com.keepgoing.keepserver.global.util;

import org.springframework.stereotype.Component;

@Component
public class NfcValidator {
    private static final String BOOK_NFC_PATTERN = "^[A-Z0-9]{8}$";

    public boolean isBookNfc(String nfcValue) {
        return nfcValue.matches(BOOK_NFC_PATTERN);
    }
}

