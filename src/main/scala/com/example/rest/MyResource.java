package com.example.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
//	private PersonRepository repo;
//	private final Calculator calculator;
//	
//	@Inject
//    public MyResource(PersonRepository repo, Calculator calculator) {
//		this.repo = repo;
//		this.calculator = calculator;
//	}
//
//
//
//    
//    @GET
//    @Path("{id}")
//    @RolesAllowed("standard")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Person get(@PathParam("id") String id, @Context SecurityContext sc) throws DataNotFoundException {
//    	Person person = repo.getAll().stream().filter(p -> p.getLastName().equals(id)).findFirst().orElse(null);
//		if(person == null) {
//			throw new DataNotFoundException(String.format("Person with id %s not found", id));
//		}
//    	return person;
//    }
}
