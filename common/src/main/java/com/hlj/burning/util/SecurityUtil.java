package com.hlj.burning.util;


import com.google.common.base.Charsets;
import com.hlj.burning.enums.EncryptAlgorithmEnum;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by hanlaijin@xiaomi.com on 18-5-31.
 */
public class SecurityUtil {

    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    public static final Charset ENCODING = Charsets.UTF_8;

    public static void main(String[] args) throws Exception {
        System.out.println(SecurityHelper.generateKey(EncryptAlgorithmEnum.AES));
//        System.out.println(genKey(EncryptAlgorithmEnum.AES));
        String aesSecrypt = "8Y+2Oq8h43y+WzmVBjm+qg==";
        String desSecrypt = "f5sHsCmw9Ms=";
        String PRIVAE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIMRuA1544kxnzdw0VugKkFQzcBuFCkHfaBCvAkGB5BLRVQDW5zkQjOXWaS+SJSkKPMahLDeHcKEU3K4wPC31XDWLwHUwh5BVvDqWadqil31VvUaKYF9PUfQgfydBSQXOXMmwRjkaepBWiFdLd+2GQuPaTKv2UT95vX5IGpf7KRvAgMBAAECgYAlvhx6BPd4skle9aXT9LM2S8Zz+z7i8b1DQPRUxN+4tXQpVS40Z6aIpLSzs/fCleMU3jYHmG6JbQF6r6kfqFBV2gs5gcFdMJYYYAtknDcCdjONJXcFlQxiCZGgAmrPk/l7Wl0Bs3clIw4jGsvi/vdbMqGCzO5oWqlAavfHefI/QQJBAOGkX0skF50UJIquyq4peVRHqV7JFLszgT5K3vDl2mwHQiDOlaGmv1WhivuoO7liDBQfxG5/eWHi9U7NcIKv8PsCQQCUtAqmVhdTzXl9Pg++7xKuACqCx+mAW0l5iA0DjTEo2Ta5AFkl/jHWFL8QQPodIW1izktw+ZMORBizHJL2m4gdAkBN1dSlZ/LZVYxxZnA1+cqHnxy2vKhxFwg7Q7p54OAiqbJO/NQy6gFQeaQmwVaw0TiXbInnEAQAFzlFkKqB+k0nAkArLKr66HwVZM8lQeOoeBlgU8QD/K8bCWYn1wyQP2pKheaRiDxZ/5fQpG1k9rinxJDR4zucLZo4xiS1vFIfL8ExAkEAomNetWwqLW8EomDwryfBuZplW7DaseWbMctm5AEXJwY7HyNUykaAVHmHPOQyFWI5usvR73dG/k7ozAHmcnHKaw==";
        String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDEbgNeeOJMZ83cNFboCpBUM3AbhQpB32gQrwJBgeQS0VUA1uc5EIzl1mkvkiUpCjzGoSw3h3ChFNyuMDwt9Vw1i8B1MIeQVbw6lmnaopd9Vb1GimBfT1H0IH8nQUkFzlzJsEY5GnqQVohXS3fthkLj2kyr9lE/eb1+SBqX+ykbwIDAQAB";
        String sign = "F+Dyjx9TsPYsnTCXiRdgpN7+8dcvxEkHnCWya+sGVk8GzSysOtidV/J09k6ai7wSEe/Af6NkBMlaK0zxXKmZ2hY71MKeCqyfHXiYA2oMBrbKpOI5uuJgKC3+Dl3gt0fw+LyGBv3lU72YabOuxpT+3ZwjJI4WN+XmPeBYEwfUTSc=";
        String rsaSecrypt = "RPHTuZip6pjhB6vlbT4eSTEXVFiczy2se/SOddEtPC41LMmssP41ZIFDCWDzHSmVUTLAMvzs3XZiQSVPnUmjWOn6JZdmGc0nOKWgDJ5FDBc2xdvugrq5v55RJneJVdbMIT9tv1eOhvDW/i+myKm7tGx+4quSlApHvI38cuS/EFI=";

        String s = "我需要被加密heh";
        System.out.println("sha1 = " + sha1(s));
        System.out.println("md5 = " + md5(s));

        System.out.println("base64 = " + new String(encodeBase64(s.getBytes()), Charsets.UTF_8));
        System.out.println(new String(decodeBase64("5oiR6ZyA6KaB6KKr5Yqg5a+GaGVo".getBytes()), Charsets.UTF_8));

        System.out.println("base64 = " + encodeBase64Str(s));
        System.out.println(decodeBase64Str("5oiR6ZyA6KaB6KKr5Yqg5a+GaGVo"));

        System.out.println("des = " + encryptDes(s, desSecrypt));
        System.out.println(decryptDes("ImqVYMdOpA/rbGJlLDAYlv3R84uAOY+r", desSecrypt));

        System.out.println("aes = " + encryptAes(s, aesSecrypt));
        System.out.println(decryptAes("8/aEVutHpqwns/6F/NG1xBBHB5ogIpUdERBq+Jtshpc=", aesSecrypt));

        System.out.println("sign = " + signByPrivateKey(s, PRIVAE_KEY));
        System.out.println(verifySignByPublicKey(s, sign, PUBLIC_KEY));

        System.out.println("rsa = " + encryptByPublicKey(s, PUBLIC_KEY));
        System.out.println(decryptByPrivateKey(rsaSecrypt, PRIVAE_KEY));
    }

    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        byte[] result = md.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] result = md.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static byte[] encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    public static byte[] decodeBase64(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }

    public static String encodeBase64Str(String input) {
        return new BASE64Encoder().encode(input.getBytes());
    }

    public static String decodeBase64Str(String input) throws IOException {
        byte[] result = new BASE64Decoder().decodeBuffer(input);
        return new String(result, ENCODING);
    }

    public static String encryptDes(String input, String secretKey) throws Exception {
        SecretKey key = loadKey(secretKey, EncryptAlgorithmEnum.DES);
        byte[] bytes = encrypt(input.getBytes(), key, EncryptAlgorithmEnum.DES);
        byte[] base64Bytes = encodeBase64(bytes);
        return new String(base64Bytes, ENCODING);
    }

    public static String decryptDes(String input, String secretKey) throws Exception {
        SecretKey key = loadKey(secretKey, EncryptAlgorithmEnum.DES);
        byte[] base64Bytes = decodeBase64(input.getBytes());
        byte[] bytes = decrypt(base64Bytes, key, EncryptAlgorithmEnum.DES);
        return new String(bytes, ENCODING);
    }

    public static String encryptAes(String input, String secretKey) throws Exception {
        SecretKey key = loadKey(secretKey, EncryptAlgorithmEnum.AES);
        byte[] bytes = encrypt(input.getBytes(), key, EncryptAlgorithmEnum.AES);
        byte[] base64Bytes = encodeBase64(bytes);
        return new String(base64Bytes, ENCODING);
    }

    public static String decryptAes(String input, String secretKey) throws Exception {
        SecretKey key = loadKey(secretKey, EncryptAlgorithmEnum.AES);
        byte[] base64Bytes = decodeBase64(input.getBytes());
        byte[] bytes = decrypt(base64Bytes, key, EncryptAlgorithmEnum.AES);
        return new String(bytes, ENCODING);
    }


    public static String signByPrivateKey(String input, String privateKey) throws Exception {
        PrivateKey priKey = getPrivateKey(privateKey);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(input.getBytes(ENCODING));

        byte[] signed = signature.sign();
        byte[] base64Bytes = encodeBase64(signed);
        return new String(base64Bytes, ENCODING);
    }

    public static boolean verifySignByPublicKey(String input, String sign, String publicKey) throws Exception {
        PublicKey pubKey = getPublicKey(publicKey);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(input.getBytes(ENCODING));

        byte[] base64Bytes = decodeBase64(sign.getBytes());
        return signature.verify(base64Bytes);
    }

    public static String encryptByPublicKey(String input, String publicKey) throws Exception {
        PublicKey pubKey = getPublicKey(publicKey);
        byte[] bytes = encrypt(input.getBytes(), pubKey, EncryptAlgorithmEnum.RSA);
        byte[] base64Bytes = encodeBase64(bytes);
        return new String(base64Bytes, ENCODING);
    }

    public static String decryptByPrivateKey(String input, String privateKey) throws Exception {
        PrivateKey priKey = getPrivateKey(privateKey);
        byte[] base64Bytes = decodeBase64(input.getBytes());
        byte[] bytes = decrypt(base64Bytes, priKey, EncryptAlgorithmEnum.RSA);
        return new String(bytes, ENCODING);
    }

    private static byte[] encrypt(byte[] input, Key key, EncryptAlgorithmEnum algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(input);
    }

    private static byte[] decrypt(byte[] input, Key key, EncryptAlgorithmEnum algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(input);
    }

    private static SecretKey loadKey(String base64Key, EncryptAlgorithmEnum algorithm) {
        byte[] bytes = decodeBase64(base64Key.getBytes());
        SecretKey key = new SecretKeySpec(bytes, algorithm.getAlgorithm());
        return key;
    }

    private static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = decodeBase64(key.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(EncryptAlgorithmEnum.RSA.getAlgorithm());
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = decodeBase64(key.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(EncryptAlgorithmEnum.RSA.getAlgorithm());
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    static class SecurityHelper{
        // 生成对称加密秘钥
        public static String generateKey(EncryptAlgorithmEnum algorithm) throws NoSuchAlgorithmException {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.getAlgorithm());
            keyGenerator.init(algorithm.getBit());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] result = encodeBase64(secretKey.getEncoded());
            return new String(result, ENCODING);
        }

        // 生成非对称加密秘钥
        public static KeyPair getKeyPair(EncryptAlgorithmEnum algorithm) throws NoSuchAlgorithmException {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm.getAlgorithm());
            keyPairGenerator.initialize(algorithm.getBit());
            return keyPairGenerator.generateKeyPair();
        }

        // 获得非对称加密公钥
        public static String getPublicKey(KeyPair keyPair) {
            PublicKey publicKey = keyPair.getPublic();
            byte[] result = encodeBase64(publicKey.getEncoded());
            return new String(result, ENCODING);
        }

        // 获得非对称加密私钥
        public static String getPrivateKey(KeyPair keyPair) {
            PrivateKey privateKey = keyPair.getPrivate();
            byte[] result = encodeBase64(privateKey.getEncoded());
            return new String(result, ENCODING);
        }
    }
}
