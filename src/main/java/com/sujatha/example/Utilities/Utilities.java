package com.sujatha.example.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	
	public static String concatinateStrings(String ...args) {
		StringBuilder sb = new StringBuilder();
	    for (String arg : args) {
	    	sb.append(arg).append(" ");
	     }
	    return sb.toString();
	}
	
	public String protectPassword(String password) {
		AES256 aes=new AES256();
		String encrypt = null;
		try {
			encrypt = aes.encyrpt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encrypt;
	}

	public String retrieveProtectedPassword(String password) {
		AES256 aes=new AES256();
		String decrypt = null;
		try {
			decrypt = aes.decrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decrypt;
	}
    
    public static void main(String args[]) throws Exception {
    	AES256 utilities = new AES256();
    	String password = "Suja*3456_THA";
    	getCurrentTimeStamp();
    	//String ePass = utilities.encyrpt(password);
    	//String dPass = utilities.decrypt(ePass);
    	//System.out.println("Original Password:"+password+ " \nEncrypted:"+ePass+" \nDecrypted:"+dPass);
    }
    public static Date getCurrentTimeStamp() {
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	Date date = new Date();    	
    	//System.out.println(date);
    	return date;
    }
}
