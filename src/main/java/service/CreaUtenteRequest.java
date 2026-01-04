package service;

import com.auth0.jwt.exceptions.JWTVerificationException;

import dao.UtentePostgresDAO;
import dto.CreaUtenteDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("users")
public class CreaUtenteRequest 
{
	@Path("createUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response creaUtente(CreaUtenteDTO utente, @HeaderParam("Token") String token)
	{
		try {
			long idUtente = new TokenGenerator(System.getenv("JWT_SECRET")).validateAdminTokenAndGetID(token);
			if(new UtentePostgresDAO().createUser(utente))
				return Response.status(Response.Status.OK).build();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (JWTVerificationException e) {
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity(e.getMessage()).build();	
		}catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(e.getMessage()).build();
		}
	}

}
