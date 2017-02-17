package com.example.rest;

import java.security.Principal;
import java.util.List;

import javax.ws.rs.core.SecurityContext;

public class MySecurityContext implements SecurityContext {
	private final MyUser user;
	private final String scheme;
	private final List<String> roles;
	
	public MySecurityContext(MyUser user, String scheme, List<String> roles) {
		this.user = user;
		this.scheme = scheme;
		this.roles = roles;
	}

	@Override
	public Principal getUserPrincipal() {
		return user;
	}

	@Override
	public boolean isUserInRole(String role) {
		return roles.contains(role);
	}

	@Override
	public boolean isSecure() {
		return "https".equalsIgnoreCase(this.scheme);
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}
}
