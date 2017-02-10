package ldapProto

import org.scalatest.{FlatSpec, Matchers}

class LDAPAuthenticationSpec extends FlatSpec with Matchers {
  val serverEndpoint = "LDAP://"
  val fullDomain="example.org"
  val domain = "example"
  val validUsername = "admin"
  val validPassword = "admin"
  val ldapAuthentication = LDAPAuthentication(serverEndpoint, fullDomain, domain)

  "login" should "return None for invalid username and password" in {
    val result = ldapAuthentication.login("test", "invalidPassword")
    assert(result.isEmpty)
  }

  "login" should "return Some(UserInfo) for valid username and password" in {
    val result = ldapAuthentication.login(validUsername, validPassword)
    assert(result.get.username == validUsername)
  }

  "login" should "return firstName and lastName for valid username and password" in {
    val result = ldapAuthentication.login(validUsername, validPassword)
    assert(result.get.firstName == "Meisam")
    assert(result.get.lastName == "E")
  }

  "login" should "return users group memberships" in {
    val result = ldapAuthentication.login(validUsername, validPassword)
    assert(result.get.groups.contains("administrators"))
  }
}
