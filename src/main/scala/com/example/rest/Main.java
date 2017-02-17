package com.example.rest;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.rest package
        final ResourceConfig rc = new MyResourceConfig().packages("com.example.rest");
        
        final Map<String, Object> initParams = new HashMap<String, Object>();
        
        initParams.put("com.sun.jersey.config.property.packages", "rest");
        initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
// 
        //initParams.put("com.sun.jersey.spi.container.ContainerRequestFilters", SecurityFilter.class.getName());
        rc.addProperties(initParams);
        rc.register(new DependencyBinder());

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        /*
        HttpHandler staticContentHandler = new CLStaticHttpHandler(HttpServer.class.getClassLoader(), "/static/");
        server.getServerConfiguration().addHttpHandler(staticContentHandler, "/static");
        */

        StaticHttpHandler staticHandler = new StaticHttpHandler("./static");
        staticHandler.setFileCacheEnabled(false);
		server.getServerConfiguration().addHttpHandler(
                staticHandler, "/static");
        
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

