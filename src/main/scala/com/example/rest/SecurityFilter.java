package com.example.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

public class SecurityFilter implements ContainerRequestFilter {
	private final UserRepository repo;
	
	@Inject
	public SecurityFilter(UserRepository repo) {
		this.repo = repo;
	}
	@Override
	public void filter(ContainerRequestContext containerRequest) throws IOException {
		// GET, POST, PUT, DELETE, ...
		String method = containerRequest.getMethod();
		// myresource/get/56bCA for example
		String path = containerRequest.getUriInfo().getPath(true);

		// We do allow wadl to be retrieve
		if (method.equals("GET") && (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))) {
			return;
		}

		// Get the authentification passed in HTTP headers parameters
		String auth = containerRequest.getHeaderString("authorization");

		// If the user does not have the right (does not provide any HTTP Basic
		// Auth)
		SecurityContext secCtx = new MySecurityContext(new MyUser("anonymous"), "", new ArrayList<String>());
		if (auth != null) {
			// lap : loginAndPassword
			String[] lap = BasicAuth.decode(auth);

			// If login or password fail
			if (lap != null && lap.length == 2) {
				// DO YOUR DATABASE CHECK HERE (replace that line behind)...
				MyUser user = null;
				if (repo.check(lap[0], lap[1])) {
					user = new MyUser(lap[0]);
				}

				// Our system refuse login and password
				if (user == null) {
					throw new WebApplicationException(Status.UNAUTHORIZED);
				}

				// We configure your Security Context here
				String scheme = containerRequest.getUriInfo().getRequestUri().getScheme();
				List<String> roles = new ArrayList<String>();
				roles.add("standard");
				roles.add("advanced");
				secCtx = new MySecurityContext(user,  scheme,  roles);
			}

		}

		containerRequest.setSecurityContext(secCtx);

		// TODO : HERE YOU SHOULD ADD PARAMETER TO REQUEST, TO REMEMBER USER ON
		// YOUR REST SERVICE...
	}
}
