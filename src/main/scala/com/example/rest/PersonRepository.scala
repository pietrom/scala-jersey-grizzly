package com.example.rest

trait PersonRepository {
  def getAll() : List[Person]
}