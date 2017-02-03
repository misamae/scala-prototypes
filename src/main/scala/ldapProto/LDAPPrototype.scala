package ldapProto

import java.util
import javax.naming.Context
import javax.naming.ldap.InitialLdapContext

object LDAPPrototype {

  def main(args: Array[String]): Unit = {
    val ldapAdServer = "LDAP://"
    val ldapSearchBase = "dc=parsagroup,dc=local"
    val env = new util.Hashtable[String, Object]()
    env.put(Context.SECURITY_AUTHENTICATION, "simple")
    env.put(Context.SECURITY_PRINCIPAL, "PARSAGROUP\\m.emamjome")
    env.put(Context.SECURITY_CREDENTIALS, "testing12345.")

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")
    env.put(Context.PROVIDER_URL, ldapAdServer)

    env.put("java.naming.ldap.attributes.binary", "objectSID")

    // the following is helpful in debugging errors
    // env.put("com.sun.jndi.ldap.trace.ber", System.err)

    val ctx = new InitialLdapContext(env, null)

    val ldap = new LDAPTest()

    //1) lookup the ldap account
    val srLdapUser = ldap.findAccountByAccountName(ctx, ldapSearchBase, "test")
    println(s"found user with id: $srLdapUser")

    //2) get the SID of the users primary group
    val primaryGroupSID = ldap.getPrimaryGroupSID(srLdapUser)
    println(s"found primary Group SID: $srLdapUser")
    //
    //    3) get the users Primary Group
    val primaryGroupName = ldap.findGroupBySID(ctx, ldapSearchBase, primaryGroupSID)
    println(s"primary group name: $primaryGroupName")
  }

}
