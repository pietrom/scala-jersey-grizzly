package com.example.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
//import javax.ws.rs.ext.Provider;

//@Provider
public class EntityNotFoundMapper implements ExceptionMapper<DataNotFoundException> {
	  public Response toResponse(DataNotFoundException ex) {
	    return Response.status(404).
	      entity(ex.getMessage()).
	      type("text/plain").
	      build();
	  }
	}
