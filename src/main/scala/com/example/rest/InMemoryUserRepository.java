package com.example.rest;

import java.util.Arrays;

public class InMemoryUserRepository implements UserRepository {
	private static final String[] USERNAMES = { "username", "pietro", "test" };

	@Override
	public boolean check(String username, String password) {
		return "password".equals(password) && (Arrays.binarySearch(USERNAMES, username) >= 0);
	}

}
