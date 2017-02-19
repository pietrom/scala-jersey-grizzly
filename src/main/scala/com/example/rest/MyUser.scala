package com.example.rest;

import java.security.Principal;
import java.util.Random;

object MyUser {
  private val rnd = new Random()
}

class MyUser(val username: String) extends Principal {
  val weight = 40 + 60 * MyUser.rnd.nextInt()

  override def getName() = username
}
