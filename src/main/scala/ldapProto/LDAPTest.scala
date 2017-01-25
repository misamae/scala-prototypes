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

//  public String findGroupBySID(DirContext ctx, String ldapSearchBase, String sid) throws NamingException {
//
//    String searchFilter = "(&(objectClass=group)(objectSid=" + sid + "))";
//
//    SearchControls searchControls = new SearchControls();
//    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//
//    NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase, searchFilter, searchControls);
//
//    if(results.hasMoreElements()) {
//      SearchResult searchResult = (SearchResult) results.nextElement();
//
//      //make sure there is not another item available, there should be only 1 match
//      if(results.hasMoreElements()) {
//        System.err.println("Matched multiple groups for the group with SID: " + sid);
//        return null;
//      } else {
//        return (String)searchResult.getAttributes().get("sAMAccountName").get();
//      }
//    }
//    return null;
//  }

//  public String getPrimaryGroupSID(SearchResult srLdapUser) throws NamingException {
//    byte[] objectSID = (byte[])srLdapUser.getAttributes().get("objectSid").get();
//    String strPrimaryGroupID = (String)srLdapUser.getAttributes().get("primaryGroupID").get();
//
//    String strObjectSid = decodeSID(objectSID);
//
//    return strObjectSid.substring(0, strObjectSid.lastIndexOf('-') + 1) + strPrimaryGroupID;
//  }

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
//  public static String decodeSID(byte[] sid) {
//
//    final StringBuilder strSid = new StringBuilder("S-");
//
//    // get version
//    final int revision = sid[0];
//    strSid.append(Integer.toString(revision));
//
//    //next byte is the count of sub-authorities
//    final int countSubAuths = sid[1] & 0xFF;
//
//    //get the authority
//    long authority = 0;
//    //String rid = "";
//    for(int i = 2; i <= 7; i++) {
//      authority |= ((long)sid[i]) << (8 * (5 - (i - 2)));
//    }
//    strSid.append("-");
//    strSid.append(Long.toHexString(authority));
//
//    //iterate all the sub-auths
//    int offset = 8;
//    int size = 4; //4 bytes for each sub auth
//    for(int j = 0; j < countSubAuths; j++) {
//      long subAuthority = 0;
//      for(int k = 0; k < size; k++) {
//        subAuthority |= (long)(sid[offset + k] & 0xFF) << (8 * k);
//      }
//
//      strSid.append("-");
//      strSid.append(subAuthority);
//
//      offset += size;
//    }
//
//    return strSid.toString();
//  }
}