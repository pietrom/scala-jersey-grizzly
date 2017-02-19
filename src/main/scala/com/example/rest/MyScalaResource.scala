package com.example.rest

import scala.collection.JavaConverters._
import javax.ws.rs.Path
import javax.inject.Inject
import javax.ws.rs.Produces
import javax.ws.rs.GET
import javax.ws.rs.core.MediaType
import javax.ws.rs.QueryParam

@Path("myscalaresource")
class MyScalaResource @Inject() (private val repo: PersonRepository) {
  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getAll(): List[Person] = {
    val people = repo.getAll()
    people
  }

  @GET
  @Path("add")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def add(@QueryParam("x") x: Int, @QueryParam("y") y: Int) = x + y
}