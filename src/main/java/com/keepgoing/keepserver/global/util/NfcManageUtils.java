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
    private final String characterTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private int certCharLength = 8;
    private final SecureRandom random;

    public NfcManageUtils() {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String executeGenerate() {
        int tableLength = characterTable.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < certCharLength; i++) {
            sb.append(characterTable.charAt(random.nextInt(tableLength)));
        }

        return sb.toString();
    }

}