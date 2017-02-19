package com.example.rest

import scala.collection.JavaConverters._
import org.glassfish.jersey.server.ResourceConfig
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.Priorities
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature
import org.glassfish.hk2.utilities.binding.AbstractBinder
import org.glassfish.hk2.utilities.Binder
import javax.ws.rs.ApplicationPath
import javax.ws.rs.ext.ContextResolver
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

class AppConfig extends ResourceConfig {
    packages("com.example.rest")
    val props : Map[String, Object] = Map("com.sun.jersey.config.property.packages" -> "rest",
        "com.sun.jersey.api.json.POJOMappingFeature" -> "true")
    addProperties(props.asJava)
    
    register(new ObjectMapperResolver(), classOf[ContextResolver[ObjectMapper]])
    register(new EntityNotFoundMapper(), classOf[ExceptionMapper[DataNotFoundException]])
    register(classOf[SecurityFilter], Priorities.AUTHENTICATION)
		register(classOf[RolesAllowedDynamicFeature], Priorities.AUTHORIZATION)
		register(new AppDependencyBinder(), classOf[Binder])
		
		class ObjectMapperResolver extends ContextResolver[ObjectMapper] {
      private val mapper = new ObjectMapper()
      mapper.registerModule(DefaultScalaModule)
      
      override def getContext(clazz : Class[_]) = {
        mapper
      }
    }
}