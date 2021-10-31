package br.com.cristalia.biblioteca.util;

/**
 *
 * @author rafael
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

public class Cript {

    public static String md5(String input) throws NoSuchAlgorithmException {
        if (null == input) {
            return null;
        }
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
        //Converts message digest value in base 16 (hex) 
        String md5 = new BigInteger(1, digest.digest()).toString(16);
        return md5;
    }

    public static String SHA(String input) throws NoSuchAlgorithmException {
        if (null == input) {
            return null;
        }
        //Create MessageDigest object for sha2
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
        //Converts message digest value in base 16 (hex) 
        String sha2 = new BigInteger(1, digest.digest()).toString(16);
        return sha2;
    }

    public static String SHAHex(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder hexString = new StringBuilder();
        if (null == input) {
            return null;
        }
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(input.getBytes("UTF-8"));

        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

    /**
     * Codifica string na base 64 (Encoder)
     */
    public static String codifica(String msg) throws Exception {
        return new String(Base64.encodeBase64(msg.getBytes()));
    }

    /**
     * Codifica long na base 64 (Encoder)
     */
    public static String codifica(Long msg) throws Exception {
        return new String(Base64.encodeBase64(String.valueOf(msg).getBytes()));
    }

    /**
     * Decodifica string na base 64 (Decoder)
     */
    public static String decodifica(String msg) throws Exception {
        return new String(Base64.decodeBase64(msg.getBytes()));
    }

    /**
     * Decodifica long na base 64 (Decoder)
     */
    public static String decodifica(Long msg) throws Exception {
        return new String(Base64.decodeBase64(String.valueOf(msg).getBytes()));
    }

}
