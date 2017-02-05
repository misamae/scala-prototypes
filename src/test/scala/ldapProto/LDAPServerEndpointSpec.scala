package ldapProto

import org.scalatest.{FlatSpec, Matchers}

class LDAPServerEndpointSpec extends FlatSpec with Matchers {

  "LDAPServerEndpoint" should "setup domain dc" in {
    val domain = "parsagroup.local"
    val serverEndpoint = "LDAP://"
    val endpoint = LDAPServerEndpoint(serverEndpoint, domain, "")
    assert(endpoint.ldapSearchBase == "dc=parsagroup,dc=local")
  }

}
