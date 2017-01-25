package ldapProto

import java.util
import javax.naming.Context
import javax.naming.ldap.InitialLdapContext

object LDAPPrototype {

  def main(args: Array[String]): Unit = {
    //    val ldap = new LdapNetworkConnection("localhost", 389)
    val ldapAdServer = "ldap://ad.yourserver.com:389"
    val ldapSearchBase = "dc=ad,dc=my-domain,dc=com"

    val ldapUsername = "administrator"
    val ldapPassword = "password"

    val ldapAccountToLookup = "m.emmamjome@parsagroup.biz"

    val env = new util.Hashtable[String, Object]()

    env.put(Context.SECURITY_AUTHENTICATION, "simple")
    env.put(Context.SECURITY_PRINCIPAL, ldapUsername)
    env.put(Context.SECURITY_CREDENTIALS, ldapPassword)

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")
    env.put(Context.PROVIDER_URL, ldapAdServer)

    //ensures that objectSID attribute values
    //will be returned as a byte[] instead of a String
    env.put("java.naming.ldap.attributes.binary", "objectSID")

    // the following is helpful in debugging errors
    env.put("com.sun.jndi.ldap.trace.ber", System.err)

    println("before context???")
    val ctx = new InitialLdapContext(env, null)

    val ldap = new LDAPTest()

    println("finding account by Account name")

    //1) lookup the ldap account
    val srLdapUser = ldap.findAccountByAccountName(ctx, ldapSearchBase, ldapAccountToLookup)

    //2) get the SID of the users primary group
    //    String primaryGroupSID = ldap.getPrimaryGroupSID(srLdapUser)
    //
    //    3) get the users Primary Group
    //    String primaryGroupName = ldap.findGroupBySID(ctx, ldapSearchBase, primaryGroupSID)
  }

}
