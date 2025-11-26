package service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCode256 
{
	public static String getHashCode256(String input) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] code = md.digest(input.getBytes(StandardCharsets.UTF_8));
		//Conversione in esadecimale
		StringBuilder hashCode = new StringBuilder();
        for (byte b : code) {
            String hex = Integer.toHexString(0xff & b);	//Valore unsigned
            if (hex.length() == 1) 
            	hashCode.append('0');
            hashCode.append(hex);
        }
        return hashCode.toString();
	}
	
	public static void main(String[] args)
	{
		try {
			System.out.println(HashCode256.getHashCode256("libro"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
