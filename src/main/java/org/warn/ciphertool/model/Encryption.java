package org.warn.ciphertool.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Encryption {

	public String encrypt(String message, String algorithm, String keyFile) {

		try {
			SecretKey key = readKey(keyFile);
			Cipher ecipher = getEncryptionCipher(algorithm, key);
			byte[] utf8 = message.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);
			return Base64.getEncoder().encodeToString(enc);
		}

		catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String decrypt(String cipherText, String algorithm, String keyFile) {

		try {
			SecretKey key = readKey(keyFile);
			Cipher dcipher = getDecryptionCipher(algorithm, key);
			byte[] dec = Base64.getDecoder().decode(cipherText);
			byte[] utf8 = dcipher.doFinal(dec);
			return new String(utf8, "UTF8");
		}

		catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean writeKey(String filePathAndName, String algorithm) {

		// generate key
		SecretKey key = null;
		try {
			KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
			key = keygen.generateKey();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}

		// save key in a file
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePathAndName));
			out.writeObject(key);
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public SecretKey readKey(String filePathAndName) {

		SecretKey key = null;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePathAndName));
			key = (SecretKey) in.readObject();
			in.close();
			return key;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Cipher getEncryptionCipher(String algorithm, SecretKey key) {

		try {
			Cipher ecipher = Cipher.getInstance(algorithm);
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			return ecipher;
		}

		catch (javax.crypto.NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (java.security.InvalidKeyException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Cipher getDecryptionCipher(String algorithm, SecretKey key) {

		try {
			Cipher dcipher = Cipher.getInstance(algorithm);
			dcipher.init(Cipher.DECRYPT_MODE, key);
			return dcipher;
		}

		catch (javax.crypto.NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (java.security.InvalidKeyException e) {
			e.printStackTrace();
		}

		return null;
	}

}
