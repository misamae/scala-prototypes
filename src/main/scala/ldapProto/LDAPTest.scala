package ldapProto

import java.util.Hashtable
import javax.naming.Context
import javax.naming.NamingEnumeration
import javax.naming.NamingException
import javax.naming.directory.DirContext
import javax.naming.directory.SearchControls
import javax.naming.directory.SearchResult
import javax.naming.ldap.InitialLdapContext
import javax.naming.ldap.LdapContext

class LDAPTest {

  def findAccountByAccountName(ctx: DirContext , ldapSearchBase: String , accountName: String ) = {

    val searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + "))"

    val searchControls = new SearchControls()
    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE)

    val results: NamingEnumeration[SearchResult] = ctx.search(ldapSearchBase, searchFilter, searchControls)

    println("results???")
    var searchResult: SearchResult = null
    if(results.hasMoreElements()) {

      searchResult = results.nextElement()
//      searchResult = (SearchResult) results.nextElement()

      //make sure there is not another item available, there should be only 1 match
      if(results.hasMoreElements()) {
        System.err.println("Matched multiple users for the accountName: " + accountName);
        null
      }
    }

    searchResult
  }

  def findGroupBySID(ctx: DirContext , ldapSearchBase: String, sid: String) = {

    val searchFilter = "(&(objectClass=group)(objectSid=" + sid + "))"

    val searchControls = new SearchControls()
    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE)

//    NamingEnumeration < SearchResult > results = ctx.search(ldapSearchBase, searchFilter, searchControls);
    val results = ctx.search(ldapSearchBase, searchFilter, searchControls)

    if (results.hasMoreElements()) {
//      val searchResult = (SearchResult)
      val t = results.nextElement()
      //
      //make sure there is not another item available, there should be only 1 match
//      if (results.hasMoreElements()) {
//        System.err.println("Matched multiple groups for the group with SID: " + sid);
//        return null;
//      } else {
//        return (String) searchResult
//        .getAttributes().get("sAMAccountName").get();
//      }
    }
    null
  }

  def getPrimaryGroupSID(srLdapUser: SearchResult ) = {
    val objectSID = srLdapUser.getAttributes().get("objectSid").get().asInstanceOf[Array[Byte]]
    val strPrimaryGroupID = srLdapUser.getAttributes().get("primaryGroupID").get().asInstanceOf[String]

    val strObjectSid = decodeSID(objectSID)

    strObjectSid.substring(0, strObjectSid.lastIndexOf('-') + 1) + strPrimaryGroupID
  }

  /**
    * The binary data is in the form:
    * byte[0] - revision level
    * byte[1] - count of sub-authorities
    * byte[2-7] - 48 bit authority (big-endian)
    * and then count x 32 bit sub authorities (little-endian)
    *
    * The String value is: S-Revision-Authority-SubAuthority[n]...
    *
    * Based on code from here - http://forums.oracle.com/forums/thread.jspa?threadID=1155740&tstart=0
    */
  def decodeSID(sid: Array[Byte]) = {

    val strSid = new StringBuilder("S-")

    // get version
    val revision = sid(0)
    strSid.append(Integer.toString(revision))

    //next byte is the count of sub-authorities
    val countSubAuths = sid(0) & 0xFF

    //get the authority
    var authority = 0L
    //String rid = "";
    for(i <- Range(2, 8)){
      authority |= sid(0).asInstanceOf[Long] << (8 * (5 - (i - 2)))
    }

    strSid.append("-")
    strSid.append(authority.toHexString)

    //iterate all the sub-auths
    var offset = 8
    val size = 4 //4 bytes for each sub auth
    for(_ <- Range(0, countSubAuths)) {

      var subAuthority = 0
      for(k <- Range(0, size)) {
        subAuthority |= ((sid(offset + k).asInstanceOf[Long] & 0xFF) << (8 * k)).toInt
      }

      strSid.append("-")
      strSid.append(subAuthority)

      offset += size
    }

    strSid.toString()
  }
}