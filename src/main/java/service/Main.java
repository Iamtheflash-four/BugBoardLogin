package service;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main 
{
	public static void main(String[] args)
	{
		ResourceConfig rc = new ResourceConfig();
		rc.register(LoginRequest.class);
		
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
			URI.create("http://localhost:8080"), rc);
	}
}
 