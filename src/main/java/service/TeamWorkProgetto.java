package service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import dao.ProgettoPostgresDAO;
import dao.TeamWorkPostgresDAO;
import dao.UtentePostgresDAO;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/teamwork")
public class TeamWorkProgetto 
{
	@Path("/progetto/add")
	@PUT
	public Response addProgetto(@HeaderParam("token") String token, @HeaderParam("email") String email,
			@HeaderParam("idTeam") long idTeam)
	{
		try {
			new TokenGenerator(System.getenv("JWT_SECRET"))
				.validateUserTokenAndGetID(token);
			long idUtente = new UtentePostgresDAO().getUserID(email);
			boolean success = new TeamWorkPostgresDAO().addUser(idUtente, idTeam);
		
			if(success)
				return Response.status(Response.Status.OK).build();
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
