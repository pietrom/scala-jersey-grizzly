package com.example.rest;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPersonRepository implements PersonRepository {

	private final List<Person> persons = new ArrayList<Person>();

	public InMemoryPersonRepository() {
		persons.add(new Person("Eddie", "Merckx"));
		persons.add(new Person("Fausto", "Coppi"));
	}

	@Override
	public List<Person> getAll() {
		return persons;
	}

}
