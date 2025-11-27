package service;

import java.sql.SQLException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import dao.UtentePostgresDAO;
import dto.ChangePasswordDTO;
import dto.ChangePasswordResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class ChangePasswordRequest 
{
	private Response response;
	
    @POST
    @Path("/change-password")
    @Consumes(MediaType.APPLICATION_JSON)   
    @Produces(MediaType.APPLICATION_JSON)   		
    public Response changePassword(ChangePasswordDTO dto) 
    {
    	response = null;
        int idUtente = verifyToken(dto.getToken());
        if( response != null)
        	return response;
        response = checkRequest(dto);
        if( response != null)
        	return response;
            		
        // Esegui il cambio password
        return changePasword(dto, idUtente);
    }

	private Response changePasword(ChangePasswordDTO dto, int idUtente) 
	{
		try{
            new UtentePostgresDAO().changePassword(
                idUtente, 
                HashCode256.getHashCode256(dto.getOldPassword()), 
                HashCode256.getHashCode256(dto.getNewPassword())
            );
            
            return Response.status(Response.Status.OK)
                    .entity(new ChangePasswordResponse(true, "Password aggiornata con successo"))
                    .build();
        } 
        catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ChangePasswordResponse(false,  e.getMessage()))
                    .build();
        }
	}

    private int verifyToken(String token)
    {
    	try 
    	{
            int idUtente = new TokenGenerator(System.getenv("JWT_SECRET")).validateTokenAndGetUserID(token);
            return idUtente;
    	} 
    	catch (TokenExpiredException e) 
    	{
    		 response = Response.status(Response.Status.UNAUTHORIZED)
                     .entity(new ChangePasswordResponse(false, "Token scaduto"))
                     .build();
    	}
        catch (JWTVerificationException e) {
            response = Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ChangePasswordResponse(false, "Token non valido"))
                    .build();
        }
        return -1;
    }
    
	private Response checkRequest(ChangePasswordDTO dto) {
		// Validazione password
		if(dto.getOldPassword() == null || dto.getOldPassword().isEmpty()) {
		    return Response.status(Response.Status.BAD_REQUEST)
		            .entity(new ChangePasswordResponse(false, "Password attuale mancante"))
		            .build();
		}
		
		if(dto.getNewPassword() == null || dto.getNewPassword().isEmpty()) {
		    return Response.status(Response.Status.BAD_REQUEST)
		            .entity(new ChangePasswordResponse(false, "Nuova password mancante"))
		            .build();
		}
		
		if(dto.getOldPassword().equals(dto.getNewPassword())) {
		    return Response.status(Response.Status.BAD_REQUEST)
		            .entity(new ChangePasswordResponse(false, "La nuova password deve essere diversa da quella attuale"))
		            .build();
		}
		return null;
	}
}
