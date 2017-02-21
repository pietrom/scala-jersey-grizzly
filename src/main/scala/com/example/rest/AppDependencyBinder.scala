package com.example.rest

import org.glassfish.hk2.utilities.binding.AbstractBinder
import javax.inject.Singleton

class AppDependencyBinder extends AbstractBinder {
  override def configure() : Unit = {
    bind(classOf[InMemoryPersonRepository]).to(classOf[PersonRepository]).in(classOf[Singleton])
		bind(classOf[InMemoryUserRepository]).to(classOf[UserRepository])
		bind(classOf[SimpleCalculator]).to(classOf[Calculator])
		bind(classOf[SimpleCalculator]).to(classOf[Calculator2])
  }
}