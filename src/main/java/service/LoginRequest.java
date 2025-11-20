package service;

import java.sql.SQLException;

import dao.UtentePostgresDAO;
import dto.LoginDTO;
import dto.LoginResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import entity.*;

@Path("/auth")
public class LoginRequest 
{
	@POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)   
    @Produces(MediaType.APPLICATION_JSON)   		
    public Response login(LoginDTO dto) 
	{
		try {
			Utente utente = new UtentePostgresDAO().getUserByCredentials(dto.getEmail(), dto.getPassword());
//			System.out.println(utente);
			return Response.status(Response.Status.OK)
                    .entity(new LoginResponse(true, utente.toString(), utente ))
                    .build();
		} 
		catch (SQLException e) {
			//e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new LoginResponse(false, e.getMessage(), null)).build();
		}
		catch (Exception e) {
			//e.printStackTrace();
			return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new LoginResponse(false, e.getMessage(), null)).build();
		}
	}
}
	
