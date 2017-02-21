package com.example.rest

import scala.collection.mutable.{ ListBuffer => List }

class InMemoryPersonRepository extends PersonRepository {
  private val people: List[Person] = List(new Person("Fausto", "Coppi"),
    new Person("Alberto", "Contador"),
    new Person("Miguel", "Indurain"),
    new Person("Eddy", "Merckx"))

  override def getAll() = people

  override def save(person: Person): Unit = {
    people += person
  }
}