package com.aviroop.customerservice.util;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {

    public static void main() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 256-bit key
        secureRandom.nextBytes(key);
        String secretKey = Base64.getEncoder().encodeToString(key);
        System.out.println("Generated Secret Key: " + secretKey);
    }

}
