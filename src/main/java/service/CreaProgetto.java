package service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import dao.ProgettoPostgresDAO;
import dao.TeamWorkPostgresDAO;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("progetto")
public class CreaProgetto 
{
	@Path("/create")
	@PUT
	public Response creaProgetto(@HeaderParam("token") String token, @HeaderParam("nome") String nomeProgetto,
			@HeaderParam("idTeam") long idTeam)
	{
		try {
			long idUtente = new TokenGenerator(System.getenv("JWT_SECRET"))
				.validateUserTokenAndGetID(token);
			
			long idProgetto = new ProgettoPostgresDAO().creaProgetto(nomeProgetto);
			boolean success = false;
			if(idProgetto >0)
				success = new TeamWorkPostgresDAO().creaProgetto(idProgetto, idTeam);
		
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
