/*
 * Taken from https://mkyong.com/java/java-aes-encryption-and-decryption/
 */
package com.example.github.api;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES-GCM inputs - 12 bytes IV, need the same IV and secret keys for
 * encryption and decryption.
 * <p>
 * The output consist of iv, password's salt, encrypted content and auth tag
 * in the following format: output = byte[] {i i i s s s c c c c c c ...}
 * <p>
 * i = IV bytes s = Salt bytes c = content bytes (encrypted content)
 */
public class EncryptorAesGcmPassword {
  public static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

  public static final int TAG_LENGTH_BIT = 128; // must be one of {128, 120, 112, 104, 96}
  public static final int IV_LENGTH_BYTE = 12;
  public static final int SALT_LENGTH_BYTE = 16;
  public static final Charset UTF_8 = StandardCharsets.UTF_8;

  // return a base64 encoded AES encrypted text
  public static String encrypt(byte[] pText, String password) throws Exception {

    // 16 bytes salt
    byte[] salt = CryptoUtils.getRandomNonce(SALT_LENGTH_BYTE);

    // GCM recommended 12 bytes iv?
    byte[] iv = CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);

    // secret key from password
    SecretKey aesKeyFromPassword = CryptoUtils.getAESKeyFromPassword(password.toCharArray(), salt);

    Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

    // ASE-GCM needs GCMParameterSpec
    cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

    byte[] cipherText = cipher.doFinal(pText);

    // prefix IV and Salt to cipher text
    byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length).put(iv).put(salt)
        .put(cipherText).array();

    // string representation, base64, send this string to other for decryption.
    return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);

  }

  // we need the same password, salt and iv to decrypt it
  public static String decrypt(String cText, String password) throws Exception {

    byte[] decode = Base64.getDecoder().decode(cText.getBytes(UTF_8));

    // get back the iv and salt from the cipher text
    ByteBuffer bb = ByteBuffer.wrap(decode);

    byte[] iv = new byte[IV_LENGTH_BYTE];
    bb.get(iv);

    byte[] salt = new byte[SALT_LENGTH_BYTE];
    bb.get(salt);

    byte[] cipherText = new byte[bb.remaining()];
    bb.get(cipherText);

    // get back the aes key from the same password and salt
    SecretKey aesKeyFromPassword = CryptoUtils.getAESKeyFromPassword(password.toCharArray(), salt);

    Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

    cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

    byte[] plainText = cipher.doFinal(cipherText);

    return new String(plainText, UTF_8);

  }
}
