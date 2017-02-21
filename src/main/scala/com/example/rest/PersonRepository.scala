package com.example.rest

trait PersonRepository {
  def getAll() : Seq[Person]
  
  def save(person: Person)
}