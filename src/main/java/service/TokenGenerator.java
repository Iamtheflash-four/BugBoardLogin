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
	
	public String generateToken(int idUtente) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 24); // durata 24 ore

        return JWT.create()
                .withSubject(Integer.toString(idUtente))
                .withIssuedAt(new Date())
                .withExpiresAt(cal.getTime())
                .sign(algorithm);
    }
	
	public int validateTokenAndGetUserID(String token) {
        try {
            return Integer.parseInt(verifier.verify(token).getSubject());	//idUtente se il token è valido, null altrimenti
        } catch (final JWTVerificationException verificationEx) {
            throw new JWTVerificationException("Token non valido");
        }
    }
}
