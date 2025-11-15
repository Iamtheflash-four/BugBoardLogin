package service;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class TokenGenerator 
{
	private Algorithm algorithm;
	private JWTVerifier verifier;
	
	public TokenGenerator(String secret) {
        this.algorithm = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(algorithm).build();
    }
	
	public String generateToken(String username) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 24); // durata 24 ore

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(cal.getTime())
                .sign(algorithm);
    }
	
	public String validateTokenAndGetUsername(String token) {
        try {
            return verifier.verify(token).getSubject();	//Username se il token è valido, null altrimenti
        } catch (final JWTVerificationException verificationEx) {
            return null;
        }
    }
}
