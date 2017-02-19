package com.example.rest

trait UserRepository {
  def check(username: String, password: String) : Boolean
}