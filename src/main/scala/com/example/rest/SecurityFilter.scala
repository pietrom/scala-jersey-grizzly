package com.example.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
 class SecurityFilter @Inject() (private val repo: UserRepository) extends ContainerRequestFilter {
	override def filter(containerRequest : ContainerRequestContext) : Unit = {
		// GET, POST, PUT, DELETE, ...
		val method = containerRequest.getMethod()
		// myresource/get/56bCA for example
		val path = containerRequest.getUriInfo().getPath(true)

		// We do allow wadl to be retrieve
		if (method.equals("GET") && (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))) {
			return
		}

		// Get the authentification passed in HTTP headers parameters
		val auth = containerRequest.getHeaderString("authorization");

		// If the user does not have the right (does not provide any HTTP Basic Auth)
		var secCtx = new MySecurityContext(new MyUser("anonymous"), "", List[String]());
		
		if (auth != null) {
			// lap : loginAndPassword
			val lap = BasicAuth.decode(auth)

			// If login or password fail
			if (lap != null && lap.length == 2) {
				// DO YOUR DATABASE CHECK HERE (replace that line behind)...
				var user : MyUser = null
				if (repo.check(lap(0), lap(1))) {
					user = new MyUser(lap(0))
				}

				// Our system refuse login and password
				if (user == null) {
					throw new WebApplicationException(Status.UNAUTHORIZED);
				}

				// We configure your Security Context here
				var scheme = containerRequest.getUriInfo().getRequestUri().getScheme()
				val roles = List[String]("standard", "advanced")
				secCtx = new MySecurityContext(user,  scheme,  roles)
			}

		}

		containerRequest.setSecurityContext(secCtx);
		// TODO : HERE YOU SHOULD ADD PARAMETER TO REQUEST, TO REMEMBER USER ON
		// YOUR REST SERVICE...
	}
}
