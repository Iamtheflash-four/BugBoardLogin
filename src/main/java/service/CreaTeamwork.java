package service;

import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import dao.TeamWorkPostgresDAO;
import dao.UtentePostgresDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;

@Path("teamwork")
public class CreaTeamwork 
{
	@Path("creazione")
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public Response creaTeamWork(@HeaderParam("token") String token, @HeaderParam("nome") String nomeTeam,
		@HeaderParam("email") String emailResponsabile	)	
	{
		try {
			long idUtente = new TokenGenerator(System.getenv("JWT_SECRET"))
					.validateAdminTokenAndGetID(token);
			
			long idResponsabile = new UtentePostgresDAO().getUserID(emailResponsabile);
			
			boolean success = new TeamWorkPostgresDAO().creaTeamWork(idResponsabile, emailResponsabile);
			
			if(success)
				return Response.status(Response.Status.CREATED).build();
			else
				return Response.status(204).entity("Team non creato").build();
		} 
		catch (TokenExpiredException e) {
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity("Token scaduto").build();
		}
		catch(JWTVerificationException e) {
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity("Token non valido").build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
		}
	}
}