/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secapp;

/**
 *
 * @author Dieudo M
 */
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Queue;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.print.DocFlavor.STRING;
public class encryption2 {
    
//	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException
//	{	
//		Encryption enc = new Encryption();
//			
//		// Encrypt and upload to database
//		byte[] b = Encryption.encryptMessage("Hello");
//		String s = Arrays.toString(b);   // You then upload s to database
//		System.out.println(s);	
//		
//		// Download s from database and decrypt
//		byte[] b2 = convertToBytes(s);
//		String s2 = Encryption.decryptMessage(b2);
//		System.out.println(s2);
//	}
	
	
	

public static byte[] convertToBytes(String str)
	{
		String[] byteValues = str.substring(1, str.length() - 1).split(",");
    	int keyLength = byteValues.length;
    	
    	byte[] encoded = new byte[keyLength];
   
    	for(int i=0; i<keyLength; i++)
        {
            encoded[i] = Byte.parseByte(byteValues[i].trim());
        }
    	
    	return encoded;
	}
    
}
