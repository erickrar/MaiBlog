package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class FastI18nMessage {

	
	private static ResourceBundle bundle;
	
	 static {
		 bundle = ResourceBundle.getBundle("messages");
	 }
	 
	 public static String getMessage(String key){
		 return bundle.getString(key);
	 }
	 
	 public static void changeBundle(String language){
		 bundle = ResourceBundle.getBundle("messages", new Locale(language));
	 }
}