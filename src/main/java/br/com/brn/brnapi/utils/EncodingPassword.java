package br.com.brn.brnapi.utils;
/*
 * @author BRUN
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodingPassword {

	public  static String encoding(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

		StringBuilder hexPwd = new StringBuilder();
		for (byte b : messageDigest) {
			hexPwd.append(String.format("%02X", 0xFF & b));
		}
		return hexPwd.toString();
	}

}
