package com.pgms.admin.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user-1 on 5/7/15.
 */
public class AdminUtils {

    public static String generateSalt() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public static String encrypt(String salt, String password) throws Exception {
        try {
            String encryptedPassword = Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest((password + salt).getBytes()));
            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Unable to encrypt password");
        }
    }
}
