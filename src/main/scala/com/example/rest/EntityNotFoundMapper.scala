package com.example.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

class EntityNotFoundMapper extends ExceptionMapper[DataNotFoundException] {
  override def toResponse(ex: DataNotFoundException) = {
    Response.status(404).entity(ex.getMessage()).`type`("text/plain").build()
  }
}
