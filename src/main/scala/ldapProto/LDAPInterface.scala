package ldapProto

import java.util
import javax.naming.directory.{SearchControls, SearchResult}
import javax.naming.ldap.InitialLdapContext
import javax.naming.{Context, NamingEnumeration}

case class UserInfo(username: String,
                    firstName: String,
                    lastName: String,
                    groups: Array[String])

case class LDAPServerEndpoint(serverEndpoint: String, fullDomain: String, domain: String) {
  val ldapSearchBase: String = fullDomain.split(Array('.')).map(s => s"dc=$s").mkString(",")
}

trait LDAPAuthentication {
  def login(username: String, password: String): Option[UserInfo]
}

class LDAPAuthenticationImplementation(endpoint: LDAPServerEndpoint) extends LDAPAuthentication {
  private def getEnvironmentMap(username: String, password: String): util.Hashtable[String, Object] = {
    val env = new util.Hashtable[String, Object]()
    env.put(Context.SECURITY_AUTHENTICATION, "simple")

    env.put(Context.SECURITY_PRINCIPAL, s"cn=admin,dc=example,dc=org")
//    env.put(Context.SECURITY_PRINCIPAL, s"${endpoint.domain}\\$username")
    env.put(Context.SECURITY_CREDENTIALS, password)
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")
    env.put(Context.PROVIDER_URL, endpoint.serverEndpoint)
    env.put("java.naming.ldap.attributes.binary", "objectSID")
    // the following is helpful in debugging errors
    // env.put("com.sun.jndi.ldap.trace.ber", System.err)
    env
  }
  private def getMemberships(attributes: String): Array[String] = {
    attributes.split(Array(','))
      .map(_.toLowerCase())
      .filter(s => s.contains("cn"))
      .map(s => s.replace("cn=", "").trim)
  }

  override def login(username: String, password: String): Option[UserInfo] = {
    try{
      // in case we can create context user is authenticated
      val ctx = new InitialLdapContext(getEnvironmentMap(username, password), null)

//      val searchFilter = s"(&(objectClass=user)(sAMAccountName=$username))"
      val searchFilter = s"cn=meisam emamjome"

      val searchControls = new SearchControls()
      searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE)

      val results: NamingEnumeration[SearchResult] = ctx.search(endpoint.ldapSearchBase, searchFilter, searchControls)

      var searchResult: SearchResult = null
      if(results.hasMoreElements) {
        searchResult = results.nextElement()
        //make sure there is not another item available, there should be only 1 match
        if(results.hasMoreElements) {
//          System.err.println("Matched multiple users for the accountName: " + accountName);
//          null
          None
        } else {
          val attributes = searchResult.getAttributes
          val firstName = attributes.get("givenname").get().asInstanceOf[String]
          val lastName = attributes.get("sn").get().asInstanceOf[String]
          val memberships = getMemberships(attributes.get("memberof").get().asInstanceOf[String])

          Some(UserInfo(username, firstName, lastName, memberships))
        }
      } else {
        None
      }

    }
    catch {
      case _: javax.naming.AuthenticationException => None
    }
  }
}

object LDAPAuthentication {
  def apply(serverEndpoint: String, fullDomain: String, domain: String) =
    new LDAPAuthenticationImplementation(LDAPServerEndpoint(serverEndpoint, fullDomain, domain))
}

