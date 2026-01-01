package service;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import dao.UtentePostgresDAO;
import dto.UtenteInfoDTO;
import jakarta.ws.rs.HeaderParam;

@Path("elenchi")
public class ElencoUtentiService 
{
	@Path("utenti")
	public Response creaElencoUtenti(@HeaderParam("progetto") Long idProgetto, @HeaderParam("token") String token)
	{
		try {
			long idUtente = new TokenGenerator(System.getenv("JWT_SECRET")).validateAdminTokenAndGetID(token);
			ArrayList<UtenteInfoDTO> elenco =  new UtentePostgresDAO().getElencoUteniByIdProgetto(idProgetto);
			
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