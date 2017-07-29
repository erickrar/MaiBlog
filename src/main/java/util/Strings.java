package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;


public class Strings extends StringUtils {

	public static String criptography(String text){
		MessageDigest msgDigest = null;
		String hashValue = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(text.getBytes("UTF-8"));
			byte rawByte[] = msgDigest.digest();
			hashValue = new String(DigestUtils.shaHex(Base64.encodeBase64(rawByte)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashValue;
	}

	public static String onlyNumbers(String str) {
		return str!=null?  str.replaceAll("[^0123456789]", "") : "";
	}

	public static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i]
					& 0xFF) | 0x100).substring(1,3));        
		}
		return sb.toString();
	}
	
	public static String md5Hex (String message) {
		try {
			MessageDigest md = 	MessageDigest.getInstance("MD5");
			return hex (md.digest(message.getBytes("CP1252")));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				e.printStackTrace();
		}
		return "";
	}
	
	public static String removeSpacesFromStartAndEnd(String str){
		return str.replaceAll("^\\s+|\\s+$", "");
	}
	
	public static String toSlug(String input) {
		if(input == null || input.isEmpty()) return "";
		Pattern NONLATIN = Pattern.compile("[^\\w-]");
		Pattern WHITESPACE = Pattern.compile("[\\s]");
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		return slug.toLowerCase(Locale.ENGLISH);
	}
	
	public static String randomString(int totalChars){
		return totalChars > 0 ? RandomStringUtils.randomAlphanumeric(totalChars).toUpperCase(): ""  ;
	}
	
	public static String fileSeparator(){
		return  System.getProperty("file.separator");
	}
}