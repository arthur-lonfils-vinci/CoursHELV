package be.vinci.main;

import be.vinci.api.filters.CORSResponseFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/reflect-api/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in be.vinci package
        final ResourceConfig rc = new ResourceConfig()
                .packages("be.vinci.api")
                .property("jersey.config.server.tracing.type", "ALL")
                .property("jersey.config.server.tracing.threshold", "VERBOSE")
                .property("jersey.config.server.wadl.disableWadl", true)
                .register(CORSResponseFilter.class);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.printf("Jersey app started available at %s\nHit enter to stop it...%n", BASE_URI);
        System.out.printf("Try  %s\n%n", BASE_URI + "classes");
        System.in.read();
        server.stop();
    }
}

