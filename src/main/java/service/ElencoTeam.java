package service;

import java.util.ArrayList;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import dao.TeamWorkPostgresDAO;
import dto.TeamDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/teamwork")
public class ElencoTeam 
{
	@Path("/elenchi/team")
	@GET
	public Response creaElenco(@HeaderParam("token") String token)
	{
		try {
			long idUtente = new TokenGenerator(System.getenv("JWT_SECRET")).validateUserTokenAndGetID(token);
			ArrayList<TeamDTO> elenco = new TeamWorkPostgresDAO().elencaTeamUtente(idUtente);
			if(elenco == null || elenco.isEmpty())
				System.out.println("Vuoto");
			return Response.status(Response.Status.OK)
					.entity(elenco).build();
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
