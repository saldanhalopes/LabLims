package br.com.lablims.util;

/**
 *
 * @author rafael
 */
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cript {

    public static String md5(String input) {
        String md5 = null;
        if (null == input) {
            return null;
        }
        try {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());
            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {

        }
        return md5;
    }

    public static String SHA(String input) {
        String sha2 = null;
        if (null == input) {
            return null;
        }
        try {
            //Create MessageDigest object for sha2
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());
            //Converts message digest value in base 16 (hex) 
            sha2 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {

        }
        return sha2;
    }
}
