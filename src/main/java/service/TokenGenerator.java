package service;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;


import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Calendar;
import java.util.Date;



public class TokenGenerator 
{
	private Algorithm algorithm;
	private JWTVerifier verifier;
	
	public TokenGenerator(String secret) {
        this.algorithm = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(algorithm).build();
    }
	
	public String generateToken(int idUtente, boolean admin) 
	{
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 24); // durata 24 ore

        return JWT.create()
                .withSubject(Integer.toString(idUtente))
                .withClaim("Admin", admin)
                .withIssuedAt(new Date())
                .withExpiresAt(cal.getTime())
                .sign(algorithm);
    }
	
	public int validateUserTokenAndGetID(String token) throws Exception
	{
       return Integer.parseInt(verifier.verify(token).getSubject());	//ID se il token è valido, null altrimenti
    }
	
	public int validateAdminTokenAndGetID(String token) throws JWTVerificationException 
	{
		DecodedJWT verify = verifier.verify(token);	
        if( verify.getClaim("Admin").asBoolean() == true )
          	return Integer.parseInt(verify.getSubject());
        else 
        	throw new JWTVerificationException("permesso non valido");
	}
}

