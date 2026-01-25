package com.learning.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.UUID;

public final class TimeHashGenerator {

    private  TimeHashGenerator() {}

    public static String userId(){
        try{
            long timestamp = Instant.now().toEpochMilli();
            UUID uuid = UUID.randomUUID();
            String rawInput = timestamp + "-" + uuid;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawInput.getBytes(StandardCharsets.UTF_8));
            String encoded = Base62Encoder.encode(hash);
            return encoded.substring(0, 10);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
