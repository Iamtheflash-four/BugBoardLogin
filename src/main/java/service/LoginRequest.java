package service;

import dto.LoginDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/auth")
public class LoginRequest 
{
	@POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)   // accetta JSON
    @Produces(MediaType.APPLICATION_JSON)   		// risponde in JSON
    public String login(LoginDTO dto) 
	{
        if ("user@mail.com".equals(dto.getEmail()) && "1234".equals(dto.getPassword())) 
            return "Riuscito";
        else 
            return "Fallito"; 
	}
}
	
