package service;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {
    public static void main(String[] args) {
        // Legge la porta da variabile d'ambiente, default 8080 se non definita
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        ResourceConfig rc = new ResourceConfig()
        .packages("service", "dto")
        .register(org.glassfish.jersey.jsonb.JsonBindingFeature.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
            URI.create("http://0.0.0.0:" + port), rc);

        System.out.println("Server avviato su http://localhost:" + port);
    }
}

 



