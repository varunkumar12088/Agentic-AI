package com.learning.util;

import java.math.BigInteger;

public final class Base62Encoder {

    private static final char[] BASE62 =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final BigInteger BASE = BigInteger.valueOf(62);

    private Base62Encoder() {
    }

    public static String encode(byte[] input) {
        BigInteger value = new BigInteger(1, input);
        StringBuilder result = new StringBuilder();

        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = value.divideAndRemainder(BASE);
            result.append(BASE62[divRem[1].intValue()]);
            value = divRem[0];
        }

        return result.reverse().toString();
    }
}
