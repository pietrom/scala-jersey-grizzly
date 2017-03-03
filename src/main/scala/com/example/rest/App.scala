package com.example.rest

import java.io.IOException
import java.net.URI

import org.glassfish.grizzly.http.server._
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig
import scala.collection.mutable.Map
import scala.collection.JavaConverters._
import org.glassfish.hk2.utilities.binding.AbstractBinder
import org.glassfish.grizzly.websockets.WebSocketAddOn
import org.glassfish.grizzly.websockets.WebSocketEngine
import org.glassfish.jersey.process.JerseyProcessingUncaughtExceptionHandler
import jersey.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer
import org.glassfish.jersey.grizzly2.httpserver.ContainerBuilder

//import org.glassfish.tyrus.server.Server
//import org.glassfish.tyrus.container.grizzly.server.WebSocketAddOn

object App extends App {
    val BASE_URI = "http://localhost:1919/myapp/"

    def startServer(): HttpServer = {
        val rc = new AppConfig()
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc)
    }
    
    val server = HttpServer.createSimpleServer("./static", "localhost", 1919);
    
    
    
    
    //val listener = new NetworkListener("grizzly", host, port);
    val listener = server.getListener("grizzly")
    listener.getTransport().getWorkerThreadPoolConfig().setThreadFactory(new ThreadFactoryBuilder()
                .setNameFormat("grizzly-http-server-%d")
                .setUncaughtExceptionHandler(new JerseyProcessingUncaughtExceptionHandler())
                .build());
        val secure = false
        listener.setSecure(secure);
//        if (sslEngineConfigurator != null) {
//            listener.setSSLEngineConfig(sslEngineConfigurator);
//        }
    
//    server.addListener(listener);

        // Map the path to the processor.
        val config = server.getServerConfiguration()
        val handler = ContainerBuilder.buildContainer(new AppConfig())
        if (handler != null) {            
            config.addHttpHandler(handler, "/api")
        }

        config.setPassTraceRequest(true);

//        if (start) {
//            try {
//                // Start the server.
//                server.start();
//            } catch (final IOException ex) {
//                server.shutdownNow();
//                throw new ProcessingException(LocalizationMessages.FAILED_TO_START_SERVER(ex.getMessage()), ex);
//            }
//        }
    
    //val server = startServer()
//    val staticHandler = new StaticHttpHandler("./static")
//    staticHandler.setFileCacheEnabled(false)
//    server.getServerConfiguration().addHttpHandler(staticHandler, "/static");

//    System.out.println(String.format("Jersey app started with WADL available at "
//        + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
//    
    val addon = new WebSocketAddOn()
    
    for(listener <- server.getListeners().asScala) {
        listener.registerAddOn(addon)
    }
    
    val chatApplication = new ChatApplication()
    WebSocketEngine.getEngine().register("/sockets", "/chat", chatApplication);
    
    server.start()
    System.in.read()
    server.stop()
}
