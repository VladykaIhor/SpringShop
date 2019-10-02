package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SaltHashingUtil {
    private static final Logger logger = LoggerFactory.getLogger(SaltHashingUtil.class);

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String saltAndHashPassword(String password, byte[] salt) {
        StringBuilder hashPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] hashBytes = messageDigest.digest(password.getBytes());
            for (byte b : hashBytes) {
                hashPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("Can't find algorithm for hashing", e);
        }
        return hashPassword.toString();
    }


}
