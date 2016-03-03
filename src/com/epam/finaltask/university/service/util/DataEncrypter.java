package com.epam.finaltask.university.service.util;

import java.security.NoSuchAlgorithmException;


/**
 * Data MD5 encrypter.
 */
public class DataEncrypter {

    private static final String MD5 = "MD5";

    /**
     * Encrypt string.
     *
     * @param toEncrypt to encrypt
     * @return the encrypted string
     */
    public static String encrypt(String toEncrypt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(MD5);
            byte[] array = md.digest(toEncrypt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //todo: logging
            return toEncrypt;
        }
    }
}