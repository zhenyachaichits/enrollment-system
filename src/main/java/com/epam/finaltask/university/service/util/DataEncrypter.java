package com.epam.finaltask.university.service.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;


/**
 * Data MD5 encrypter.
 */
public class DataEncrypter {

    private static final Logger LOG = LogManager.getLogger(DataEncrypter.class.getClass());

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
            LOG.error("Couldn't encrypt data. String was returned without changes", e);
            return toEncrypt;
        }
    }
}