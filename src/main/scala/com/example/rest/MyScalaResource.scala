package com.example.rest

import javax.ws.rs.Path
import javax.inject.Inject
import javax.ws.rs.Produces
import javax.ws.rs.GET
import javax.ws.rs.core.MediaType

@Path("myscalaresource")
class MyScalaResource @Inject() (private val repo : PersonRepository ) {
  /*
   * @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> get() {
        return repo.getAll();
    }*/
  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getAll() : java.util.List[Person] = {
    repo.getAll()
  }
}