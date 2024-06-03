package com.keepgoing.keepserver.global.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class GenerateCertCharacter {
    private int certCharLength = 8;
    private final char[] characterTable = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public String excuteGenerate() {
        Random random = new Random(System.currentTimeMillis());
        int tablelength = characterTable.length;
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < certCharLength; i++) {
            buf.append(characterTable[random.nextInt(tablelength)]);
        }

        return buf.toString();
    }

}