package com.example.rest

import javax.ws.rs.core.SecurityContext

class MySecurityContext(private val user: MyUser, private val scheme: String, private val roles: List[String])
  extends SecurityContext {
  override def getUserPrincipal() = user

  override def isUserInRole(role: String) = roles.contains(role)

  override def isSecure() = "https".equalsIgnoreCase(scheme)

  override def getAuthenticationScheme() = SecurityContext.BASIC_AUTH
}
