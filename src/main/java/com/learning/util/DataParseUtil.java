package com.learning.util;

import com.learning.domain.intent.IntentType;

public class DataParseUtil {

    public static IntentType parseIntent(String raw) {
        try {
            return IntentType.valueOf(raw.trim().toUpperCase());
        } catch (Exception e) {
            return IntentType.UNKNOWN;
        }
    }
}
