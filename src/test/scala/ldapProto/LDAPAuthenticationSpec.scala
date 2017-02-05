package ldapProto

import org.scalatest.{FlatSpec, Matchers}

class LDAPAuthenticationSpec extends FlatSpec with Matchers {
  val serverEndpoint = "LDAP://"
  val fullDomain=""
  val domain = ""
  val validUsername = ""
  val validPassword = ""
  val ldapAuthentication = LDAPAuthentication(serverEndpoint, fullDomain, domain)

  "login" should "return None for invalid username and password" in {
    val result = ldapAuthentication.login("test", "invalidPassword")
    assert(result.isEmpty)
  }

  "login" should "return Some(UserInfo) for valid username and password" in {
    val result = ldapAuthentication.login(validUsername, validPassword)
    assert(result.contains(UserInfo(validUsername, "Meisam", "E")))
  }

  "login" should "return firstName and lastName for valid username and password" in {
    val result = ldapAuthentication.login(validUsername, validPassword)
    assert(result.contains(UserInfo(validUsername, "Meisam", "E")))
  }
}
