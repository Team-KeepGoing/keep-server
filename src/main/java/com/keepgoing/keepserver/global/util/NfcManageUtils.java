package com.keepgoing.keepserver.global.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Getter
@Setter
@Component
public class NfcManageUtils {
    private static final String CHARACTER_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String BOOK_NFC_PATTERN = "^[A-Z0-9]{8}$";
    private static final int CERT_CHAR_LENGTH = 8; // NFC 길이

    private final SecureRandom random;

    public NfcManageUtils() {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String executeGenerate() {
        int tableLength = CHARACTER_TABLE.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < CERT_CHAR_LENGTH; i++) {
            sb.append(CHARACTER_TABLE.charAt(random.nextInt(tableLength)));
        }

        return sb.toString();
    }

}