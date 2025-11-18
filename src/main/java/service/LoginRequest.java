package service;

import dto.LoginDTO;
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
    @Consumes(MediaType.APPLICATION_JSON)   // accetta JSON
    @Produces(MediaType.APPLICATION_JSON)   		// risponde in testo
    public Utente login(LoginDTO dto) 
	{
		System.out.println("Chiave: " + System.getenv("JWT_SECRET"));
        if ("user@mail.com".equals(dto.getEmail()) && "1234".equals(dto.getPassword())) 
            return new Utente("Sasy", "Correra", dto.getEmail(), dto.getPassword(),
            		new TokenGenerator(System.getenv("JWT_SECRET")).generateToken(dto.getEmail())
            		);
        else 
            return null; 
	}
}
	
