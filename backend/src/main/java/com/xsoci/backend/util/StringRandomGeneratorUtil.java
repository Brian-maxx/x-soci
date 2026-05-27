package com.xsoci.backend.util;

import java.security.SecureRandom;
import com.xsoci.backend.constant.RegexPattern;

public class StringRandomGeneratorUtil {
    private static final SecureRandom random = new SecureRandom();

    public static String generate(int length) {
        StringBuilder str = new StringBuilder();
        String CHARACTERS = RegexPattern.CHARACTERS;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            str.append(CHARACTERS.charAt(index));
        }

        return str.toString();
    }
}
