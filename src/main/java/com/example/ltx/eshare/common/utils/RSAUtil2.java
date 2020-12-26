package com.example.ltx.eshare.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.net.URLEncoder;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class RSAUtil2 {

    private static final String KEY_ALGORITHM = "RSA";

    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final int KEY_SIZE = 1024;
    private static final int ENCRYPT_BlOCK_SIZE = KEY_SIZE / 8 - 11;
    private static final int DECRYPT_BLOCK_SIZE = KEY_SIZE / 8;
    private static final String RSA_PADDING = "RSA/ECB/PKCS1Padding";
    private static final String CODE = "UTF-8";


    /**
     * 加密
     */
    public static String encrypt(String encryptStr, String publicKey) throws Exception {


        byte[] publicKeyBytes = Base64.decodeBase64(publicKey);
        /**
         * 中文处理（兼容前端js中文乱码问题）
         */
        encryptStr = URLEncoder.encode(encryptStr, "utf-8");
        byte[] encryptBytes = encryptStr.getBytes(CODE);

        if (encryptBytes.length <= ENCRYPT_BlOCK_SIZE) {
            return Base64.encodeBase64String(encrypt(encryptBytes, publicKeyBytes));
        } else {
            byte[] buffer = null;
            byte[] blockBytes;

            int index = ((encryptBytes.length - 1) / ENCRYPT_BlOCK_SIZE) + 1;

            for (int i = 0; i < index; i++) {
                int startIndex = i * ENCRYPT_BlOCK_SIZE;
                int endIndex = startIndex + ENCRYPT_BlOCK_SIZE;
                blockBytes = ArrayUtils.subarray(encryptBytes, startIndex, endIndex);
                if (buffer == null) {
                    buffer = encrypt(blockBytes, publicKeyBytes);
                } else {
                    buffer = ArrayUtils.addAll(buffer, encrypt(blockBytes, publicKeyBytes));
                }
            }
            return Base64.encodeBase64String(buffer);
        }
    }

    /**
     * 解密
     */
    public static String decrypt(String decryptStr, String privateKey) throws Exception {
        byte[] privateKeyBytes = Base64.decodeBase64(privateKey);

        byte[] decryptBytes = Base64.decodeBase64(decryptStr);

        if (decryptBytes.length <= DECRYPT_BLOCK_SIZE) {
            return new String(decrypt(decryptBytes, privateKeyBytes), CODE);
        } else {
            byte[] buffer = null;

            int index = ((decryptBytes.length - 1) / DECRYPT_BLOCK_SIZE) + 1;
            byte[] blockBytes;
            for (int i = 0; i < index; i++) {
                int startIndex = i * DECRYPT_BLOCK_SIZE;
                int endIndex = startIndex + DECRYPT_BLOCK_SIZE;
                blockBytes = ArrayUtils.subarray(decryptBytes, startIndex, endIndex > decryptBytes.length ? decryptBytes.length : endIndex);
                if (buffer == null) {
                    buffer = decrypt(blockBytes, privateKeyBytes);
                } else {
                    buffer = ArrayUtils.addAll(buffer, decrypt(blockBytes, privateKeyBytes));
                }
            }
            return buffer == null ? "" : new String(buffer, CODE);
        }
    }

    /**
     * 加密
     */
    private static byte[] encrypt(byte[] encryptBytes, byte[] publicKeyBytes) throws Exception {
        PublicKey publicKey = codeToPublicKey(publicKeyBytes);

        Cipher cipher = Cipher.getInstance(RSA_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(encryptBytes);
    }

    /**
     * 解密
     */
    private static byte[] decrypt(byte[] decrypt, byte[] privateKeyBytes) throws Exception {
        PrivateKey privateKey = codeToPrivateKey(privateKeyBytes);

        Cipher cipher = Cipher.getInstance(RSA_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(decrypt);
    }

    /**
     * 签名
     */
    public static String sign(String str, String privateKey) throws Exception {
        byte[] privateKeyBytes = Base64.decodeBase64(privateKey);
        byte[] data = str.getBytes(CODE);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey key = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(key);
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * 验签
     */
    public static boolean verify(String str, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        byte[] data = str.getBytes(CODE);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.decodeBase64(sign));
    }

    private static PublicKey codeToPublicKey(byte[] publicKey) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey codeToPrivateKey(byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static String[] genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
        // 将公钥和私钥保存到Map
        return new String[]{privateKeyString, publicKeyString};
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        String[] keyPair = genKeyPair();
        System.out.println(JSON.toJSONString(keyPair));
    }

}