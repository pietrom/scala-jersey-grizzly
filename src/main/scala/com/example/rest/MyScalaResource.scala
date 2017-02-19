package com.example.rest

import scala.collection.JavaConverters._
import javax.ws.rs.Path
import javax.inject.Inject
import javax.ws.rs.Produces
import javax.ws.rs.GET
import javax.ws.rs.core.MediaType
import javax.ws.rs.QueryParam
import javax.annotation.security.RolesAllowed
import javax.ws.rs.core.SecurityContext
import javax.ws.rs.PathParam
import javax.ws.rs.core.Context


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
  
  @GET
  @Path("{id}")
  @RolesAllowed(Array("standard"))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def get(@PathParam("id") id: String, @Context sc: SecurityContext) = {
    def f = (x: Person) => { x.lastName == id }
  	val person:Option[Person] = repo.getAll().find(f)
  	person match {
  	  case None => throw new DataNotFoundException("Person not found: " + id)
  	  case Some(p) => p
  	}
  }
}