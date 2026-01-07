package service;

import java.util.ArrayList;

import dao.ProgettoPostgresDAO;
import dto.ElencoProgettiResponse;
import entity.Progetto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/elenchi")
public class ElencoProgettiService 
{
	@GET
    @Path("/progetti")
    @Consumes(MediaType.APPLICATION_JSON)   
    @Produces(MediaType.APPLICATION_JSON) 
	public static Response elencaProgetti(@HeaderParam("token") String token)
	{
		try {
			int idUtente = new TokenGenerator(System.getenv("JWT_SECRET")).validateUserTokenAndGetID(token);
			
			ArrayList<Progetto> elenco = new ProgettoPostgresDAO().elencaProgettiUtente(idUtente);
			
			return Response.status(Response.Status.OK)
					.entity(elenco).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity(new ElencoProgettiResponse(e.getMessage(), null)).build();
		}
	}	
}
