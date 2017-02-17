package com.example.rest;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class DependencyBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(InMemoryPersonRepository.class).to(PersonRepository.class);
		// bind(EntityNotFoundMapper.class);
		bind(InMemoryUserRepository.class).to(UserRepository.class);
		bind(SimpleCalculator.class).to(Calculator.class);
		bind(SimpleCalculator.class).to(Calculator2.class);
	}
}
