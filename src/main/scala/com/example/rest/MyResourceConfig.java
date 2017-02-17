package com.example.rest;

import javax.ws.rs.Priorities;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class MyResourceConfig extends ResourceConfig {
	public MyResourceConfig() {
		super();
		register(new EntityNotFoundMapper());
		register(SecurityFilter.class, Priorities.AUTHENTICATION);
		register(RolesAllowedDynamicFeature.class, Priorities.AUTHORIZATION);
	}

}
