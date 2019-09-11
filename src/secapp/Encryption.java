package secapp;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption 
{
	
	static String course = "computerscience";
	static String test = "test1";
	static String msg = "Hello Master!";
	static SecretKey secretKey;
	
	//public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException 
	public Encryption() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException
        {
    	secretKey = createKey();
    	
    	//System.out.println("PlainText: " + msg);
    	
    	// Encrypt and print to screen
    	//byte [] ciphertext = encryptMessage(msg);
    	//System.out.println("CipherText: " + Arrays.toString(ciphertext));
    	
    	// Decrypt and print to screen
    	//String originalMsg = decryptMessage(ciphertext);
    	//System.out.println("Original PlainText: "+ originalMsg);	
	}
	
	// Takes a plaintext, encrypts it, and returns a ciphertext
	public static byte[] encryptMessage(String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException
    {	  
    	Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey);      
	    byte[] cipheredMsg = cipher.doFinal(msg.getBytes());      
	    return cipheredMsg;
    }
	
	// Takes a ciphertext, decrypts it, and returns original message - the plaintext
	public static String decryptMessage(byte[] msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException
    {
    	Cipher cipher = Cipher.getInstance("AES");    	
	    cipher.init(Cipher.DECRYPT_MODE, secretKey);
	    byte[] decipheredMsg = cipher.doFinal(msg);
	    return new String(decipheredMsg);
    }
	
	// Creates and returns a secret key object
	private static SecretKey createKey() throws NoSuchAlgorithmException, InvalidKeySpecException
    {
	    int iterations = 10000;
	    int keyLength = 256;
	    
	    char[] courseChars = course.toCharArray();
	    byte[] testBytes = test.getBytes();
	    
	    SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA256" );
	    PBEKeySpec spec = new PBEKeySpec( courseChars, testBytes, iterations, keyLength );
	    SecretKey temp = skf.generateSecret( spec );
	    SecretKey key = new SecretKeySpec(temp.getEncoded(), "AES");
	    
	    return key;
    }
}
