package com.example.rest;

import java.security.Principal;
import java.util.Random;

public class MyUser implements Principal {
	private final String username;
	private final int weight;
	private static final Random rnd = new Random();
	
	public MyUser(String username) {
		this.username = username;
		this.weight = 40 + 60 * rnd.nextInt();
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getWeight() {
		return weight;
	}

	@Override
	public String getName() {
		return username;
	}
}
