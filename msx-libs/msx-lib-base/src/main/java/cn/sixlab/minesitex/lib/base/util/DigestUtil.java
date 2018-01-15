/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/1/10 16:20
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.minesitex.lib.base.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];
        
        for (int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 15];
            chars[i + 1] = HEX_CHARS[b & 15];
        }
        
        return chars;
    }
    
    public static String encode(String data, String algorithm) {
        byte[] bytes = data.getBytes(Digest.UTF8);
        try {
            MessageDigest instance = MessageDigest.getInstance(algorithm);
            byte[] digest = instance.digest(bytes);
            
            return new String(encodeHex(digest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static String encodeMD5(String data) {
        return encode(data, Digest.MD5);
    }
    
    public static String encodeSHA1(String data) {
        return encode(data, Digest.SHA1);
    }
    
    public static String fakeMD5(String data) {
        return fakeMD5(data, reverse(data));
    }
    
    public static String fakeMD5(String data, String key) {
        String md5 = encodeMD5(data);
        String sha1 = encodeSHA1(key);
    
        return encodeMD5(md5+sha1);
    }
    
    private static String reverse(String data){
        return new StringBuilder(data).reverse().toString();
    }
}