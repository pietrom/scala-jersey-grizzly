package com.example.rest;

class InMemoryUserRepository extends UserRepository {
  private val USERNAMES = List("username", "pietro", "test")

  override def check(username: String, password: String) = {
    val index = USERNAMES.indexOf(username)
    "password".equals(password) && USERNAMES.contains(username)
  }
}
