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
    @Produces(MediaType.TEXT_PLAIN)   		// risponde in testo
    public String login(LoginDTO dto) 
	{
		System.out.println("Ciave: " + System.getenv("JWT_SECRET"));
        if ("user@mail.com".equals(dto.getEmail()) && "1234".equals(dto.getPassword())) 
            return new TokenGenerator(System.getenv("JWT_SECRET")).generateToken(dto.getEmail());
        else 
            return null; 
	}
}
	
