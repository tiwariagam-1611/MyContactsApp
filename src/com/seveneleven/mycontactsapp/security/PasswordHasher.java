package com.seveneleven.mycontactsapp.security;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HexFormat;

public class PasswordHasher {
    private static final SecureRandom RNG = new SecureRandom();
    private static final HexFormat HEX = HexFormat.of();

    public static String hash(String rawPassword) {
        try {
            byte[] salt = new byte[8];
            RNG.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] digest = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

            return HEX.formatHex(salt) + ":" + HEX.formatHex(digest);
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }

    // Verify password against stored hash
    public static boolean matches(String rawPassword, String stored) {
        try {
            String[] parts = stored.split(":");
            if (parts.length != 2) return false;

            byte[] salt = HEX.parseHex(parts[0]);
            byte[] expected = HEX.parseHex(parts[1]);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] actual = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

            // simple comparison
            if (actual.length != expected.length) return false;
            for (int i = 0; i < actual.length; i++) {
                if (actual[i] != expected[i]) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

