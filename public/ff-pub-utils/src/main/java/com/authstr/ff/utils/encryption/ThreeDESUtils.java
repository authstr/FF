package com.authstr.ff.utils.encryption;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

import com.authstr.ff.utils.exception.AuthstrException;

public class ThreeDESUtils
{
    private static String W = "DESede";

    public static byte[] getKeyBytes(String strKey) {
        block5 : {
            if (strKey != null && strKey.length() >= 1) break block5;
            return null;
        }
        try {
            MessageDigest alg = MessageDigest.getInstance("MD5");
            alg.update(strKey.getBytes());
            byte[] bkey = alg.digest();
            int start = bkey.length;
            byte[] bkey24 = new byte[24];
            int i = 0;
            while (i < start) {
                bkey24[i] = bkey[i];
                ++i;
            }
            i = start;
            while (i < 24) {
                bkey24[i] = bkey[i - start];
                ++i;
            }
            return bkey24;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encode(byte[] key, String content) {
        try {
            SecretKeySpec deskey = new SecretKeySpec(key, W);
            Cipher cipher = Cipher.getInstance(W);
            cipher.init(1, deskey);
            return Base64.encodeBase64String((byte[])cipher.doFinal(content.getBytes("utf-8")));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new AuthstrException("base64加密过程出错!");
        }
    }

    public static String decode(byte[] key, String encodestring) {
        try {
            SecretKeySpec deskey = new SecretKeySpec(key, W);
            Cipher cipher = Cipher.getInstance(W);
            cipher.init(2, deskey);
            byte[] decryptBytes = cipher.doFinal(Base64.decodeBase64((String)encodestring));
            return new String(decryptBytes);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new AuthstrException("base64加密过程出错!");
        }
    }

    public static void main(String[] args) {
        byte[] key = ThreeDESUtils.getKeyBytes("abasdfa");
        String encode = ThreeDESUtils.encode(key, "\u62111ab8.,.ads");
        System.out.println(encode);
        System.out.println(ThreeDESUtils.decode(key, encode));
    }
}

