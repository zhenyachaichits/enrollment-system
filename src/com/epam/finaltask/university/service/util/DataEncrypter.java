package com.epam.finaltask.university.service.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Zheny Chaichits on 04.02.16.
 */

public class DataEncrypter {

    private static final String MD5 = "MD5";

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