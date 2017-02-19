package com.example.rest

import java.io.IOException
import java.net.URI

import org.glassfish.grizzly.http.server._
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig
import scala.collection.mutable.Map
import scala.collection.JavaConverters._
import org.glassfish.hk2.utilities.binding.AbstractBinder

object App extends App {
  val BASE_URI = "http://localhost:1919/myapp/"

  def startServer(): HttpServer = {
    val rc = new AppConfig()
    GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc)
  }

  val server = startServer()
  val staticHandler = new StaticHttpHandler("./static")
  staticHandler.setFileCacheEnabled(false)
  server.getServerConfiguration().addHttpHandler(staticHandler, "/static");

  System.out.println(String.format("Jersey app started with WADL available at "
    + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
  System.in.read();
  server.stop();
}